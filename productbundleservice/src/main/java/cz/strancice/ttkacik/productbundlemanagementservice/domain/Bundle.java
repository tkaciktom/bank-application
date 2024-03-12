package cz.strancice.ttkacik.productbundlemanagementservice.domain;

import cz.strancice.ttkacik.productbundlemanagementservice.domain.statemachine.SimpleStateMachine;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import static cz.strancice.ttkacik.productbundlemanagementservice.domain.BundleState.*;
import static cz.strancice.ttkacik.productbundlemanagementservice.domain.BundleStateEvent.ACTIVATE;

@Getter
@Setter
public class Bundle {

    private String id;
    private String ownerId;
    private BusinessDealPurchase businessDealPurchase;
    private BundleState status;
    private SimpleStateMachine<BundleState, BundleStateEvent> statusStateMachine;

    public Bundle(String ownerId, BusinessDealPurchase businessDealPurchase) {
        this.id = UUID.randomUUID().toString();
        this.ownerId = ownerId;
        this.businessDealPurchase = businessDealPurchase;
        this.statusStateMachine = initStatusStateMachine();
        this.status = this.statusStateMachine.getCurrentState();
    }

    private SimpleStateMachine<BundleState, BundleStateEvent> initStatusStateMachine() {
        return new SimpleStateMachine.Builder<BundleState, BundleStateEvent>(CREATED)
                .externalTransition()
                    .from(CREATED)
                    .to(ACTIVE)
                    .on(ACTIVATE)
                    .perform((toState, event) -> this.onBundleActivated())
                .build()
                .onTransitionFinished((fromState, toState) -> this.status = toState);
    }

    public void activate() {
        if (this.statusStateMachine.canAccept(ACTIVATE)) {
            this.statusStateMachine.fireEvent(ACTIVATE);
        } else {
            throw new IllegalArgumentException("Cannot accept ACTIVATE event from state %s".formatted(this.status));
        }
    }

    public void onBundleActivated() {
    }

}
