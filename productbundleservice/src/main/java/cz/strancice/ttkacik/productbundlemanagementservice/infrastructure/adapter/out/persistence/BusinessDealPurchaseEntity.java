package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class BusinessDealPurchaseEntity {

    @Id
    private String id;
    private String ownerId;
    @ManyToOne
    @JoinColumn(name = "proposition_id")
    private BundlePropositionEntity proposition;
    private LocalDate activationStartDate = LocalDate.now();
    private LocalDate activationEndDate;

}
