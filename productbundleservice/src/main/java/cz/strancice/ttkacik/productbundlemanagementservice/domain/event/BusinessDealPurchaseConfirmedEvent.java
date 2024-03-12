package cz.strancice.ttkacik.productbundlemanagementservice.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static cz.strancice.ttkacik.productbundlemanagementservice.domain.event.DomainEvent.Type.BUSINESS_DEAL_PURCHASE_CONFIRMED;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDealPurchaseConfirmedEvent implements DomainEvent {
    private String businessDealPurchaseId;
    private String bundlePropositionId;
    private String ownerId;

    @Override
    public Type getType() {
        return BUSINESS_DEAL_PURCHASE_CONFIRMED;
    }
}
