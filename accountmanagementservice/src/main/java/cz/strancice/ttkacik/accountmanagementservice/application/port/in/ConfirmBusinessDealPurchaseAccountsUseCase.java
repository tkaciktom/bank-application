package cz.strancice.ttkacik.accountmanagementservice.application.port.in;

public interface ConfirmBusinessDealPurchaseAccountsUseCase {

    void confirmBusinessDealPurchaseAccounts(String businessDealPurchaseId, String messageId);

}
