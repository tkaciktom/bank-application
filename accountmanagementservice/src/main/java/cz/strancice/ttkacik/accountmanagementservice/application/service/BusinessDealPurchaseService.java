package cz.strancice.ttkacik.accountmanagementservice.application.service;

import cz.strancice.ttkacik.accountmanagementservice.application.port.in.CreateBusinessDealPurchaseUseCase;
import cz.strancice.ttkacik.accountmanagementservice.application.port.in.ConfirmBusinessDealPurchaseUseCase;
import cz.strancice.ttkacik.accountmanagementservice.application.port.out.AccountRepository;
import cz.strancice.ttkacik.accountmanagementservice.application.port.out.BusinessDealPurchaseRepository;
import cz.strancice.ttkacik.accountmanagementservice.domain.BankAccount;
import cz.strancice.ttkacik.accountmanagementservice.domain.BusinessDealPurchase;
import cz.strancice.ttkacik.accountmanagementservice.domain.BusinessDealPurchaseAccount;
import cz.strancice.ttkacik.accountmanagementservice.domain.exception.BusinessDealPurchaseNotFoundException;
import cz.strancice.ttkacik.accountmanagementservice.infrastructure.service.idempotency.IdempotentConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessDealPurchaseService implements
        CreateBusinessDealPurchaseUseCase,
        ConfirmBusinessDealPurchaseUseCase {

    private final BusinessDealPurchaseRepository businessDealPurchaseRepository;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    @IdempotentConsumer(messageParamName = "messageId")
    public void createBusinessDealPurchase(String businessDealPurchaseId, List<BusinessDealPurchaseAccount> accounts, String messageId) {
        businessDealPurchaseRepository.loadById(businessDealPurchaseId)
                .ifPresent(businessDealPurchaseRepository::remove);
        BusinessDealPurchase purchase = new BusinessDealPurchase(businessDealPurchaseId, accounts);
        businessDealPurchaseRepository.save(purchase);
    }

    @Override
    @Transactional
    @IdempotentConsumer(messageParamName = "messageId")
    public void confirmBusinessDealPurchase(String businessDealPurchaseId, String messageId) {
        BusinessDealPurchase purchase = businessDealPurchaseRepository.loadById(businessDealPurchaseId)
                .orElseThrow(() -> new BusinessDealPurchaseNotFoundException("Cannot find business deal purchase by id: %s".formatted(businessDealPurchaseId)));
        for(var account: purchase.getAccounts()) {
            createAccount(account.getOwnerId(), account.getType(), account.getName(), businessDealPurchaseId, account.getId());
        }
    }

    private void createAccount(String userId, String accountType, String accountName, String businessDealPurchaseId, String businessDealPurchaseAccountId) {
        BankAccount newAccount = new BankAccount(userId, accountType, accountName, businessDealPurchaseId, businessDealPurchaseAccountId);
        newAccount.open();
        accountRepository.saveAccount(newAccount);
    }
}
