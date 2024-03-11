package cz.strancice.ttkacik.accountmanagementservice.infrastructure.adapter.in.web;

import cz.strancice.ttkacik.accountmanagementservice.application.service.AccountCommandService;
import cz.strancice.ttkacik.accountmanagementservice.rest.api.AccountsApi;
import cz.strancice.ttkacik.accountmanagementservice.rest.model.BankAccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AccountQueryAdapter implements AccountsApi {

    private final AccountCommandService accountCommandService;
    private final BankAccountDtoMapper mapper;

    @Override
    public ResponseEntity<List<BankAccountDTO>> getAccountsByUserId(String userId) {
        var accountsDto = accountCommandService.getUserAccounts(userId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(accountsDto);
    }

    @Override
    public ResponseEntity<List<BankAccountDTO>> getAccountsOfTypeByUserId(String userId, String accountType) {
        var accountsDto = accountCommandService.getUserAccountsOfType(userId, accountType)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(accountsDto);
    }

    @Override
    public ResponseEntity<String> getAccountStatusByUserId(String accountId) {
        var status = accountCommandService.getAccountStatus(accountId);
        return ResponseEntity.ok(status);
    }
}
