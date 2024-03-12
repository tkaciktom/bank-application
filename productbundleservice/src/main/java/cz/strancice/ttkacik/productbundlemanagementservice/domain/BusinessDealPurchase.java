package cz.strancice.ttkacik.productbundlemanagementservice.domain;

import cz.strancice.ttkacik.productbundlemanagementservice.domain.event.BusinessDealPurchaseConfirmedEvent;
import cz.strancice.ttkacik.productbundlemanagementservice.domain.event.BusinessDealPurchaseCreatedEvent;
import cz.strancice.ttkacik.productbundlemanagementservice.domain.event.DomainEventPublisher;
import cz.strancice.ttkacik.productbundlemanagementservice.domain.statemachine.SimpleStateMachine;
import lombok.Getter;
import lombok.Setter;

import static cz.strancice.ttkacik.productbundlemanagementservice.domain.BusinessDealPurchaseState.*;
import static cz.strancice.ttkacik.productbundlemanagementservice.domain.BusinessDealPurchaseStateEvent.*;

import java.time.LocalDate;

@Getter
@Setter
public class BusinessDealPurchase {
    private String id;
    private LocalDate activationStartDate = LocalDate.now();
    private LocalDate activationEndDate;
    private BusinessDealPurchaseState status;
    private SimpleStateMachine<BusinessDealPurchaseState, BusinessDealPurchaseStateEvent> statusStateMachine;

    private final String ownerId;
    private final BundleProposition proposition;

    public BusinessDealPurchase(String ownerId, BundleProposition proposition) {
        this.ownerId = ownerId;
        this.proposition = proposition;

        DomainEventPublisher.instance()
                .publish(new BusinessDealPurchaseCreatedEvent(id, proposition.getId(), ownerId));
    }

    private SimpleStateMachine<BusinessDealPurchaseState, BusinessDealPurchaseStateEvent> initStatusStateMachine() {
        return new SimpleStateMachine.Builder<BusinessDealPurchaseState, BusinessDealPurchaseStateEvent>(CREATED)
                .externalTransition()
                .from(CREATED)
                .to(CONFIRMED)
                .on(CONFIRM)
                .perform((toState, event) -> this.onBundleConfirmed())
                .build()
                .onTransitionFinished((fromState, toState) -> this.status = toState);
    }

    public void confirm() {
        if (this.statusStateMachine.canAccept(CONFIRM)) {
            this.statusStateMachine.fireEvent(CONFIRM);
        } else {
            throw new IllegalArgumentException("Cannot accept CONFIRM event from state %s".formatted(this.status));
        }
    }

    private void onBundleConfirmed() {
        DomainEventPublisher.instance()
                .publish(new BusinessDealPurchaseConfirmedEvent(id, proposition.getId(), ownerId));
    }


}
