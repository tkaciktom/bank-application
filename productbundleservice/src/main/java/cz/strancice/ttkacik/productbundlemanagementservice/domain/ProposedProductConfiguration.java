package cz.strancice.ttkacik.productbundlemanagementservice.domain;

import lombok.Data;

@Data
public class ProposedProductConfiguration {
    private Long id;
    private ProposedProductType type;
    private int maxCount;
}
