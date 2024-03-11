package cz.strancice.ttkacik.accountmanagementservice.application.port.in;

import cz.strancice.ttkacik.accountmanagementservice.domain.BankAccount;

import java.util.List;

public interface GetUserAccountsOfTypeUseCase {

    List<BankAccount> getUserAccountsOfType(String userId, String accountType);

}
