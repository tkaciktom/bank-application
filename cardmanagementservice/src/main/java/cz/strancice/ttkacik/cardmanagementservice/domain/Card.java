package cz.strancice.ttkacik.cardmanagementservice.domain;

import cz.strancice.ttkacik.cardmanagementservice.domain.event.CardIssuedEvent;
import cz.strancice.ttkacik.cardmanagementservice.domain.event.CardRemovedEvent;
import cz.strancice.ttkacik.cardmanagementservice.domain.event.DomainEventPublisher;
import cz.strancice.ttkacik.cardmanagementservice.domain.statemachine.SimpleStateMachine;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

import static cz.strancice.ttkacik.cardmanagementservice.domain.CardState.*;
import static cz.strancice.ttkacik.cardmanagementservice.domain.CardStateEvent.*;

@Getter
@Setter
public class Card {

    private String id;
    private String referencedProductId;
    private String cardNumber;
    private String cardType; // credit, debit
    private String cardVariant; // standard, gold, platinum, titanium, black
    private CardState status;
    private String reasonClosedType;
    private final SimpleStateMachine<CardState, CardStateEvent> statusStateMachine;

    public Card(String referencedProductId, String cardType, String cardVariant) {
        this.id = UUID.randomUUID().toString();
        this.referencedProductId = referencedProductId;
        this.cardNumber = CardNumberGenerator.randomCardNumber();
        this.cardType = cardType;
        this.cardVariant = cardVariant;
        this.statusStateMachine = initStatusStateMachine();
        this.status = this.statusStateMachine.getCurrentState();
    }

    private SimpleStateMachine<CardState, CardStateEvent> initStatusStateMachine() {
        return new SimpleStateMachine.Builder<CardState, CardStateEvent>(CREATED)
                .externalTransition()
                    .from(CREATED)
                    .to(ISSUED)
                    .on(ISSUE)
                    .perform((toState, event) -> this.onCardIssued())
                .externalTransition()
                    .from(ISSUED)
                    .to(REMOVED)
                    .on(REMOVE)
                    .perform((toState, event) -> this.onCardRemoved())
                .build()
                .onTransitionFinished((fromState, toState) -> this.status = toState);
    }

    public void open() {
        if (this.statusStateMachine.canAccept(ISSUE)) {
            this.statusStateMachine.fireEvent(ISSUE);
        } else {
            throw new IllegalArgumentException("Cannot accept ISSUE event from state %s".formatted(this.status));
        }
    }

    public void close(String reasonType) {
        if (this.statusStateMachine.canAccept(REMOVE)) {
            this.reasonClosedType = reasonType;
            this.statusStateMachine.fireEvent(REMOVE);
        } else {
            throw new IllegalArgumentException("Cannot accept REMOVE event from state %s".formatted(this.status));
        }
    }

    public void onCardIssued() {
        DomainEventPublisher
                .instance()
                .publish(new CardIssuedEvent(this.id, this.referencedProductId, this.cardType, this.cardVariant));
    }

    public void onCardRemoved() {
        DomainEventPublisher
                .instance()
                .publish(new CardRemovedEvent(this.id, this.referencedProductId, this.cardType, this.reasonClosedType));
    }

}
