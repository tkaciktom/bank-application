package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.adapter.out.persistence;

import cz.strancice.ttkacik.productbundlemanagementservice.domain.BundleState;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
public class BundleEntity {

    @Id
    private String id;
    private String ownerId;
    @ManyToOne
    @JoinColumn(name = "business_deal_purchase_id")
    private BusinessDealPurchaseEntity businessDealPurchase;
    @Enumerated(EnumType.STRING)
    private BundleState status;

}
