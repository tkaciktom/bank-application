package cz.strancice.ttkacik.cardmanagementservice.domain.event;

public interface DomainEvent {
    static enum Type {
        CARD_ISSUED,
        CARD_REMOVED
    }

    Type getType();
}
