package cz.strancice.ttkacik.accountmanagementservice.application.port.in;

import cz.strancice.ttkacik.accountmanagementservice.domain.BankAccount;

public interface CreateAccountUseCase {
    BankAccount createAccount(String userId, String accountType, double initialDeposit, String messageId);
}
