package cz.strancice.ttkacik.accountmanagementservice.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountOpenedEvent implements DomainEvent {
    private String accountId;
    private String userId;
    private String accountType;
    private String businessDealPurchaseId;
    private String businessDealPurchaseAccountId;

    @Override
    public Type getType() {
        return Type.ACCOUNT_OPENED;
    }
}
