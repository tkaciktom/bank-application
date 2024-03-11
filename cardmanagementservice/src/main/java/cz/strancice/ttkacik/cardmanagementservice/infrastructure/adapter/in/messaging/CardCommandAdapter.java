package cz.strancice.ttkacik.cardmanagementservice.infrastructure.adapter.in.messaging;


import cz.strancice.ttkacik.bank.cardmanagement.IssueCardCommand;
import cz.strancice.ttkacik.bank.cardmanagement.RemoveCardCommand;
import cz.strancice.ttkacik.cardmanagementservice.application.service.CardCommandService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CardCommandAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardCommandAdapter.class);

    private final CardCommandService cardCommandService;

    @KafkaListener(topics = "card-management-commands", groupId = "card-management")
    public void listenForCommand(@Payload Object record, @Header("MESSAGE_ID") String messageId) {
        if (record instanceof ConsumerRecord<?, ?> typedRecord) {
            if (typedRecord.value() instanceof IssueCardCommand command) {
                processIssueCardCommand(command, messageId);
            } else if (typedRecord.value() instanceof RemoveCardCommand command) {
                processRemoveCardCommand(command, messageId);
            }
        }
    }

    private void processRemoveCardCommand(RemoveCardCommand command, String messageId) {
        LOGGER.info("Processing RemoveCardCommand: {} for messageId: {}", command, messageId);
        cardCommandService.removeCard(
                command.getCardId().toString(),
                command.getReasonType().toString(),
                messageId);
    }

    private void processIssueCardCommand(IssueCardCommand command, String messageId) {
        LOGGER.info("Processing IssueCardCommand: {} for messageId: {}", command, messageId);
        cardCommandService.issueCard(
                command.getReferencedProductId().toString(),
                command.getCardType().toString(),
                command.getCardVariant().toString(),
                messageId);
    }

}
