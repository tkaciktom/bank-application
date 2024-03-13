package cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class BusinessDealPurchaseEntity {
    @Id
    private String id;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<BusinessDealPurchaseAccount> accounts;
}
