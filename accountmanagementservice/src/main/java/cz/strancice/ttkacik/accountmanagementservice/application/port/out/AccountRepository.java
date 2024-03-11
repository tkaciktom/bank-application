package cz.strancice.ttkacik.accountmanagementservice.application.port.out;

import cz.strancice.ttkacik.accountmanagementservice.domain.BankAccount;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Optional<BankAccount> loadAccount(String accountId);
    void saveAccount(BankAccount bankAccount);
    void deleteAccount(BankAccount bankAccount);
    String getAccountStatusByAccountId(String accountId);

    List<BankAccount> getUserAccountsOfType(String userId, String accountType);

    List<BankAccount> getUserAccounts(String userId);
}
