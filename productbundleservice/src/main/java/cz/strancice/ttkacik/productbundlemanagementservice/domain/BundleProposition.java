package cz.strancice.ttkacik.productbundlemanagementservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BundleProposition {
    private String id;
    private String name;
    private String description;
    private List<String> benefits;
    private List<ProposedProductConfiguration> proposedProductConfigurations;
}
