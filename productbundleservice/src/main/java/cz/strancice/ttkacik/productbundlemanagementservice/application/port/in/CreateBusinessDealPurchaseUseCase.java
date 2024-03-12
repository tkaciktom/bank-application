package cz.strancice.ttkacik.productbundlemanagementservice.application.port.in;

public interface CreateBusinessDealPurchaseUseCase {

    void createBusinessDealPurchase(
            String businessDealPurchaseId,
            String propositionId,
            String ownerId,
            String messageId);

}
