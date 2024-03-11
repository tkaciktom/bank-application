package cz.strancice.ttkacik.accountmanagementservice.domain.event;

public interface DomainEvent {
    static enum Type {
        ACCOUNT_OPENED,
        ACCOUNT_CLOSED
    }

    Type getType();
}
