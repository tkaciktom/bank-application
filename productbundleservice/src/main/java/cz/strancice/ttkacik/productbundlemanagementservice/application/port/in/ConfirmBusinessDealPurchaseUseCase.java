package cz.strancice.ttkacik.productbundlemanagementservice.application.port.in;

public interface ConfirmBusinessDealPurchaseUseCase {

    void confirmBusinessDealPurchase(
            String businessDealPurchaseId,
            String propositionId,
            String ownerId,
            String messageId);

}
