package cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BusinessDealPurchaseAccountEntity {
    @Id
    private String id;
    private String businessDealPurchaseId;
    private String type;
    private String name;
    private String ownerId;
}
