package cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class BusinessDealPurchaseAccount {
    private String id;
    private String type;
    private String name;
    private String ownerId;
}
