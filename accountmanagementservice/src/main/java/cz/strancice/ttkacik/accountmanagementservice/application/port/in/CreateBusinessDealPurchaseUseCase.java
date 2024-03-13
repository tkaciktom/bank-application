package cz.strancice.ttkacik.accountmanagementservice.application.port.in;

import cz.strancice.ttkacik.accountmanagementservice.domain.BusinessDealPurchaseAccount;

import java.util.List;

public interface CreateBusinessDealPurchaseUseCase {

    void createBusinessDealPurchase(String businessDealPurchaseId, List<BusinessDealPurchaseAccount> accounts, String messageId);

}
