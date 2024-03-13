package cz.strancice.ttkacik.accountmanagementservice.application.port.out;

import cz.strancice.ttkacik.accountmanagementservice.domain.BusinessDealPurchaseAccount;

import java.util.List;
import java.util.Optional;

public interface BusinessDealPurchaseAccountRepository {

    Optional<BusinessDealPurchaseAccount> loadAccount(String id);

    Optional<BusinessDealPurchaseAccount> getAccountByBusinessDealPurchaseAccountId(String accountId);
    void saveAccount(BusinessDealPurchaseAccount account);

    List<BusinessDealPurchaseAccount> getAccountsByBusinessDealPurchaseId(String businessDealPurchaseId);

}
