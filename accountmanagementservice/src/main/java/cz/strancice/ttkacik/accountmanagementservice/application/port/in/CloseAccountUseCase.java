package cz.strancice.ttkacik.accountmanagementservice.application.port.in;

public interface CloseAccountUseCase {
    void closeAccount(String accountId, String reasonType, String messageId);
}
