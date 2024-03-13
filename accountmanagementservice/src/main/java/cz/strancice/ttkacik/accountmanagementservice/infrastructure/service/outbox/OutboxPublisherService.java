package cz.strancice.ttkacik.accountmanagementservice.infrastructure.service.outbox;

import cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence.entity.OutboxEventEntity;
import cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence.OutboxEventJpaRepository;
import cz.strancice.ttkacik.bank.accountmanagement.OpenAccountCommand;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OutboxPublisherService {

    private final OutboxEventJpaRepository outboxEventRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaMessageFactory kafkaMessageFactory;
    private final String kafkaTopicName = "account-events";

    @Scheduled(fixedDelay = 10000)
    @Transactional
    public void publishEventsFromOutbox() {
        List<OutboxEventEntity> events = outboxEventRepository.findAll();
        for (OutboxEventEntity event : events) {
            Object message = kafkaMessageFactory.toMessage(event.getDomainEventType(), event.getJsonData());
            kafkaTemplate.send(kafkaTopicName, message);
            outboxEventRepository.delete(event);
        }
    }

    @Scheduled(fixedDelay = 30000)
    public void sendOpenCommand() {
        var userId = UUID.randomUUID().toString();
        var messageId = "1234"; //UUID.randomUUID().toString();

        OpenAccountCommand command = new OpenAccountCommand(userId, "DEPOSIT", Math.random());

        ProducerRecord<String, Object> record = new ProducerRecord<>("account-management-commands", userId, command);
        record.headers().add("MESSAGE_ID", messageId.getBytes());

        kafkaTemplate.send(record);
    }
}
