package cz.strancice.ttkacik.accountmanagementservice.application.port.in;

public interface ConfirmBusinessDealPurchaseUseCase {

    void confirmBusinessDealPurchase(String businessDealPurchaseId, String messageId);

}
