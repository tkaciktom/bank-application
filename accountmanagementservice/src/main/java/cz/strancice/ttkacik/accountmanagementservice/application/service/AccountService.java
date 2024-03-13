package cz.strancice.ttkacik.accountmanagementservice.application.service;

import cz.strancice.ttkacik.accountmanagementservice.application.port.in.*;
import cz.strancice.ttkacik.accountmanagementservice.application.port.out.AccountRepository;
import cz.strancice.ttkacik.accountmanagementservice.domain.BankAccount;
import cz.strancice.ttkacik.accountmanagementservice.infrastructure.service.idempotency.IdempotentConsumer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cz.strancice.ttkacik.accountmanagementservice.domain.exception.AccountNotFoundException;

import java.util.List;

@Service
@Transactional
public class AccountService implements
        CloseAccountUseCase,
        GetAccountStatusUseCase,
        GetUserAccountsUseCase,
        GetUserAccountsOfTypeUseCase {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    @IdempotentConsumer(messageParamName = "messageId")
    public void closeAccount(String accountId, String reasonType, String messageId) {
        BankAccount bankAccount = accountRepository.loadAccount(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found for id: " + accountId));
        bankAccount.close(reasonType);
        accountRepository.deleteAccount(bankAccount);
    }

    @Override
    public String getAccountStatus(String accountId) {
        return accountRepository.getAccountStatusByAccountId(accountId);
    }

    @Override
    public List<BankAccount> getUserAccounts(String userId) {
        return accountRepository.getUserAccounts(userId);
    }

    @Override
    public List<BankAccount> getUserAccountsOfType(String userId, String accountType) {
        return accountRepository.getUserAccountsOfType(userId, accountType);
    }
}
