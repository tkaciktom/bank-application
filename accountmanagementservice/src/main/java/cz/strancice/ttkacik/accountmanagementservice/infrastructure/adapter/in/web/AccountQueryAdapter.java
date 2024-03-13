package cz.strancice.ttkacik.accountmanagementservice.infrastructure.adapter.in.web;

import cz.strancice.ttkacik.accountmanagementservice.application.service.AccountService;
import cz.strancice.ttkacik.accountmanagementservice.rest.api.AccountsApi;
import cz.strancice.ttkacik.accountmanagementservice.rest.model.BankAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AccountQueryAdapter implements AccountsApi {

    private final AccountService accountService;
    private final BankAccountDtoMapper mapper;

    @Override
    public ResponseEntity<List<BankAccountDto>> getAccountsByUserId(String userId) {
        var accountsDto = accountService.getUserAccounts(userId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(accountsDto);
    }

    @Override
    public ResponseEntity<List<BankAccountDto>> getAccountsOfTypeByUserId(String userId, String accountType) {
        var accountsDto = accountService.getUserAccountsOfType(userId, accountType)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(accountsDto);
    }

    @Override
    public ResponseEntity<String> getAccountStatusByUserId(String accountId) {
        var status = accountService.getAccountStatus(accountId);
        return ResponseEntity.ok(status);
    }
}
