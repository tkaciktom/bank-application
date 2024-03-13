package cz.strancice.ttkacik.accountmanagementservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessDealPurchaseAccount {
    private String id;
    private String businessDealPurchaseId;
    private String type;
    private String name;
    private String ownerId;
    private String instantiatedAccountId;
}
