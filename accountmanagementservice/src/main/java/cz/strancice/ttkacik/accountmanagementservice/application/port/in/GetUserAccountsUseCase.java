package cz.strancice.ttkacik.accountmanagementservice.application.port.in;

import cz.strancice.ttkacik.accountmanagementservice.domain.BankAccount;

import java.util.List;

public interface GetUserAccountsUseCase {

    List<BankAccount> getUserAccounts(String userId);

}
