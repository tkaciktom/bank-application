package cz.strancice.ttkacik.accountmanagementservice.infrastructure.adapter.in.messaging;

import cz.strancice.ttkacik.accountmanagementservice.application.service.BusinessDealPurchaseService;
import cz.strancice.ttkacik.bank.bundlemanagement.BusinessDealPurchaseConfirmedEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class BundleEventAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BundleEventAdapter.class);

    private final BusinessDealPurchaseService businessDealPurchaseService;

    @KafkaListener(topics = "bundle-events", groupId = "account-management")
    public void listenForEvent(@Payload Object record, @Header("MESSAGE_ID") String messageId) {
        if (record instanceof ConsumerRecord<?, ?> typedRecord) {
            if (typedRecord.value() instanceof BusinessDealPurchaseConfirmedEvent command) {
                processBusinessDealPurchaseConfirmedEvent(command, messageId);
            }
        }
    }
    private void processBusinessDealPurchaseConfirmedEvent(BusinessDealPurchaseConfirmedEvent event,
                                                           String messageId) {
        LOGGER.info("Processing BusinessDealPurchaseConfirmedEvent: {} for messageId: {}", event, messageId);
        businessDealPurchaseService.confirmBusinessDealPurchase(event.getBusinessDealPurchaseId().toString(), messageId);
    }

}
