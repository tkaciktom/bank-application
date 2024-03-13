package cz.strancice.ttkacik.accountmanagementservice.infrastructure.adapter.in.messaging;

import cz.strancice.ttkacik.accountmanagementservice.application.service.BusinessDealPurchaseAccountService;
import cz.strancice.ttkacik.accountmanagementservice.domain.BusinessDealPurchaseAccount;
import cz.strancice.ttkacik.bank.accountmanagement.Account;
import cz.strancice.ttkacik.bank.accountmanagement.CloseAccountCommand;
import cz.strancice.ttkacik.bank.accountmanagement.CreateBusinessDealPurchaseAccountsCommand;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import cz.strancice.ttkacik.accountmanagementservice.application.service.AccountService;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AccountCommandAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountCommandAdapter.class);

    private final AccountService accountService;
    private final BusinessDealPurchaseAccountService businessDealPurchaseAccountService;

    @KafkaListener(topics = "account-management-commands", groupId = "account-management")
    public void listenForCommand(@Payload Object record, @Header("MESSAGE_ID") String messageId) {
        if (record instanceof ConsumerRecord<?, ?> typedRecord) {
            if (typedRecord.value() instanceof CreateBusinessDealPurchaseAccountsCommand command) {
                processCreateBusinessDealPurchaseAccounts(command, messageId);
            } else if (typedRecord.value() instanceof CloseAccountCommand command) {
                processCloseAccountCommand(command, messageId);
            }
        }
    }

    private void processCloseAccountCommand(CloseAccountCommand command, String messageId) {
        LOGGER.info("Processing CloseAccountCommand: {} for messageId: {}", command, messageId);
        accountService.closeAccount(
                command.getAccountId().toString(),
                command.getReasonType().toString(),
                messageId);
    }

    private void processCreateBusinessDealPurchaseAccounts(CreateBusinessDealPurchaseAccountsCommand event,
                                                           String messageId) {
        LOGGER.info("Processing CreateBusinessDealPurchaseAccountsCommand: {} for messageId: {}", event, messageId);
        List<BusinessDealPurchaseAccount> accounts = event.getAccounts().stream()
                .map(account -> toBusinessDealPurchaseAccount(
                        account,
                        event.getBusinessDealPurchaseId().toString(),
                        event.getOwnerId().toString()))
                .toList();
        businessDealPurchaseAccountService.createBusinessDealPurchaseAccounts(accounts, messageId);
    }

    private BusinessDealPurchaseAccount toBusinessDealPurchaseAccount(Account account, String businessPurchaseId, String ownerId) {
        return BusinessDealPurchaseAccount.builder()
                .id(account.getDealId().toString())
                .businessDealPurchaseId(businessPurchaseId)
                .type(account.getType().toString())
                .name(account.getName().toString())
                .ownerId(ownerId)
                .build();
    }

}
