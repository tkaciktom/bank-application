package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class BundlePropositionEntity {
    @Id
    private Long id;
    private String name;
    private String description;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> benefits;
    @OneToMany
    @JoinColumn(name = "product_configuration_id")
    private List<ProposedProductConfigurationEntity> proposedProductConfigurations;
}
