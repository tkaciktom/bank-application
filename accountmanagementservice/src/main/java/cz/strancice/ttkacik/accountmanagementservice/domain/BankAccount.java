package cz.strancice.ttkacik.accountmanagementservice.domain;

import cz.strancice.ttkacik.accountmanagementservice.domain.event.BankAccountOpenedEvent;
import cz.strancice.ttkacik.accountmanagementservice.domain.event.BankAccountClosedEvent;
import cz.strancice.ttkacik.accountmanagementservice.domain.event.DomainEventPublisher;
import cz.strancice.ttkacik.accountmanagementservice.domain.statemachine.SimpleStateMachine;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

import static cz.strancice.ttkacik.accountmanagementservice.domain.BankAccountState.*;
import static cz.strancice.ttkacik.accountmanagementservice.domain.BankAccountStateEvent.*;

@Getter
@Setter
public class BankAccount {

    private String id;
    private String userId;
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private BankAccountState status;
    private String reasonClosedType;
    private final SimpleStateMachine<BankAccountState, BankAccountStateEvent> statusStateMachine;

    public BankAccount(String userId, String accountType) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.accountNumber = BankAccountNumberGenerator.randomAccountNumber();
        this.accountType = accountType;
        this.balance = BigDecimal.ZERO;
        this.statusStateMachine = initStatusStateMachine();
        this.status = this.statusStateMachine.getCurrentState();
    }

    private SimpleStateMachine<BankAccountState, BankAccountStateEvent> initStatusStateMachine() {
        return new SimpleStateMachine.Builder<BankAccountState, BankAccountStateEvent>(CREATED)
                .externalTransition()
                    .from(CREATED)
                    .to(OPENED)
                    .on(OPEN)
                    .perform((toState, event) -> this.onAccountOpened())
                .externalTransition()
                    .from(OPENED)
                    .to(CLOSED)
                    .on(CLOSE)
                    .perform((toState, event) -> this.onAccountClosed())
                .build()
                .onTransitionFinished((fromState, toState) -> this.status = toState);
    }

    public void open() {
        if (this.statusStateMachine.canAccept(OPEN)) {
            this.statusStateMachine.fireEvent(OPEN);
        } else {
            throw new IllegalArgumentException("Cannot move to OPENED state from %s".formatted(this.status));
        }
    }

    public void close(String reasonType) {
        if (this.statusStateMachine.canAccept(CLOSE)) {
            this.reasonClosedType = reasonType;
            this.statusStateMachine.fireEvent(CLOSE);
        } else {
            throw new IllegalArgumentException("Cannot move to CLOSED state from %s".formatted(this.status));
        }
    }

    public void onAccountOpened() {
        DomainEventPublisher
                .instance()
                .publish(new BankAccountOpenedEvent(this.id, this.userId, this.accountType));
    }

    public void onAccountClosed() {
        DomainEventPublisher
                .instance()
                .publish(new BankAccountClosedEvent(this.id, this.userId, this.accountType, this.reasonClosedType));
    }

}
