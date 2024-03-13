package cz.strancice.ttkacik.accountmanagementservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDealPurchase {
    private String id;
    private List<BusinessDealPurchaseAccount> accounts;
}
