package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.adapter.out.persistence;

import cz.strancice.ttkacik.productbundlemanagementservice.domain.ProposedProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProposedProductConfigurationEntity {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private ProposedProductType type;
    private int maxCount;
}
