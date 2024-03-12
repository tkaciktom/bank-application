package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.service.outbox;

import cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.adapter.out.persistence.OutboxEventEntity;
import cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.persistence.OutboxEventJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OutboxPublisherService {

    private final OutboxEventJpaRepository outboxEventRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaMessageFactory kafkaMessageFactory;
    private final String kafkaTopicName = "bundle-events";

    @Scheduled(fixedDelay = 2000)
    @Transactional
    public void publishEventsFromOutbox() {
        List<OutboxEventEntity> events = outboxEventRepository.findAll();
        for (OutboxEventEntity event : events) {
            Object message = kafkaMessageFactory.toMessage(event.getDomainEventType(), event.getJsonData());
            kafkaTemplate.send(kafkaTopicName, message);
            outboxEventRepository.delete(event);
        }
    }

}
