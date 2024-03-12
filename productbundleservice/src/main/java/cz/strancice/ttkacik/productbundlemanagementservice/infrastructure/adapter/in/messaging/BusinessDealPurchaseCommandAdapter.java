package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.adapter.in.messaging;

import cz.strancice.ttkacik.bank.bundlemanagement.ConfirmBusinessDealPurchaseCommand;
import cz.strancice.ttkacik.bank.bundlemanagement.CreateBusinessDealPurchaseCommand;
import cz.strancice.ttkacik.productbundlemanagementservice.application.service.BusinessDealPurchaseService;
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
public class BusinessDealPurchaseCommandAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessDealPurchaseCommandAdapter.class);

    private final BusinessDealPurchaseService businessDealPurchaseService;

    @KafkaListener(topics = "bundle-management-commands", groupId = "bundle-management")
    public void listenForBundleCommand(@Payload Object record, @Header("MESSAGE_ID") String messageId) {
        if (record instanceof ConsumerRecord<?, ?> typedRecord) {
            if (typedRecord.value() instanceof CreateBusinessDealPurchaseCommand command) {
                processCreateBusinessDealPurchaseCommand(command, messageId);
            } else if (typedRecord.value() instanceof ConfirmBusinessDealPurchaseCommand command) {
                processConfirmBusinessDealPurchaseCommand(command, messageId);
            }
        }
    }

    private void processCreateBusinessDealPurchaseCommand(CreateBusinessDealPurchaseCommand command, String messageId) {
        LOGGER.info("Processing CreateBusinessDealPurchaseCommand: {} for messageId: {}", command, messageId);
        businessDealPurchaseService.createBusinessDealPurchase(
                command.getBusinessDealPurchaseId().toString(),
                command.getBundlePropositionId().toString(),
                command.getOwnerId().toString(),
                messageId);
    }

    private void processConfirmBusinessDealPurchaseCommand(ConfirmBusinessDealPurchaseCommand command, String messageId) {
        LOGGER.info("Processing ConfirmBusinessDealPurchaseCommand: {} for messageId: {}", command, messageId);
        businessDealPurchaseService.confirmBusinessDealPurchase(
                command.getBusinessDealPurchaseId().toString(),
                command.getBundlePropositionId().toString(),
                command.getOwnerId().toString(),
                messageId);
    }

}
