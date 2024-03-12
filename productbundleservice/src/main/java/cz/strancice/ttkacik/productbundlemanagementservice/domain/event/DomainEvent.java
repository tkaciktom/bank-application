package cz.strancice.ttkacik.productbundlemanagementservice.domain.event;

public interface DomainEvent {
    enum Type {
        BUSINESS_DEAL_PURCHASE_CREATED,
        BUSINESS_DEAL_PURCHASE_CONFIRMED,
    }

    Type getType();
}
