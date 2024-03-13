package cz.strancice.ttkacik.accountmanagementservice.application.port.in;

import cz.strancice.ttkacik.accountmanagementservice.domain.BusinessDealPurchaseAccount;

import java.util.List;

public interface CreateBusinessDealPurchaseAccountsUseCase {

    void createBusinessDealPurchaseAccounts(List<BusinessDealPurchaseAccount> accounts, String messageId);

}
