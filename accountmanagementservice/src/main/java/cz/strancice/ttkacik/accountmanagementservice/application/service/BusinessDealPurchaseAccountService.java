package cz.strancice.ttkacik.accountmanagementservice.application.service;

import cz.strancice.ttkacik.accountmanagementservice.application.port.in.CreateBusinessDealPurchaseAccountsUseCase;
import cz.strancice.ttkacik.accountmanagementservice.application.port.in.ConfirmBusinessDealPurchaseAccountsUseCase;
import cz.strancice.ttkacik.accountmanagementservice.application.port.out.AccountRepository;
import cz.strancice.ttkacik.accountmanagementservice.application.port.out.BusinessDealPurchaseAccountRepository;
import cz.strancice.ttkacik.accountmanagementservice.domain.BankAccount;
import cz.strancice.ttkacik.accountmanagementservice.domain.BusinessDealPurchaseAccount;
import cz.strancice.ttkacik.accountmanagementservice.infrastructure.service.idempotency.IdempotentConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessDealPurchaseAccountService implements
        CreateBusinessDealPurchaseAccountsUseCase,
        ConfirmBusinessDealPurchaseAccountsUseCase {

    private final BusinessDealPurchaseAccountRepository businessDealPurchaseAccountRepository;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    @IdempotentConsumer(messageParamName = "messageId")
    public void createBusinessDealPurchaseAccounts(List<BusinessDealPurchaseAccount> accounts, String messageId) {
        for(var account: accounts) {
            businessDealPurchaseAccountRepository.saveAccount(account);
        }
    }

    @Override
    @Transactional
    @IdempotentConsumer(messageParamName = "messageId")
    public void confirmBusinessDealPurchaseAccounts(String businessDealPurchaseId, String messageId) {
        List<BusinessDealPurchaseAccount> accounts = businessDealPurchaseAccountRepository.getAccountsByBusinessDealPurchaseId(businessDealPurchaseId);
        for(var account: accounts) {
            createAccount(account.getOwnerId(), account.getType(), account.getName(), businessDealPurchaseId, account.getId());
        }
    }

    private void createAccount(String userId, String accountType, String accountName, String businessDealPurchaseId, String businessDealPurchaseAccountId) {
        BankAccount newAccount = new BankAccount(userId, accountType, accountName, businessDealPurchaseId, businessDealPurchaseAccountId);
        newAccount.open();
        accountRepository.saveAccount(newAccount);
    }
}
