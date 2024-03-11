package cz.strancice.ttkacik.accountmanagementservice.infrastructure.adapter.in.messaging;

import cz.strancice.ttkacik.bank.accountmanagement.CloseAccountCommand;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import cz.strancice.ttkacik.accountmanagementservice.application.service.AccountCommandService;
import cz.strancice.ttkacik.bank.accountmanagement.OpenAccountCommand;

@Service
@RequiredArgsConstructor
public class AccountCommandAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountCommandAdapter.class);

    private final AccountCommandService accountCommandService;

    @KafkaListener(topics = "account-management-commands", groupId = "account-management")
    public void listenForCommand(@Payload Object record, @Header("MESSAGE_ID") String messageId) {
        if (record instanceof ConsumerRecord<?, ?> typedRecord) {
            if (typedRecord.value() instanceof OpenAccountCommand command) {
                processOpenAccountCommand(command, messageId);
            } else if (typedRecord.value() instanceof CloseAccountCommand command) {
                processCloseAccountCommand(command, messageId);
            }
        }
    }

    private void processCloseAccountCommand(CloseAccountCommand command, String messageId) {
        LOGGER.info("Processing CloseAccountCommand: {} for messageId: {}", command, messageId);
        accountCommandService.closeAccount(
                command.getAccountId().toString(),
                command.getReasonType().toString(),
                messageId);
    }

    private void processOpenAccountCommand(OpenAccountCommand command, String messageId) {
        LOGGER.info("Processing OpenAccountCommand: {} for messageId: {}", command, messageId);
        accountCommandService.createAccount(
                command.getUserId().toString(),
                command.getAccountType().toString(),
                command.getInitialDeposit(),
                messageId);
    }

}
