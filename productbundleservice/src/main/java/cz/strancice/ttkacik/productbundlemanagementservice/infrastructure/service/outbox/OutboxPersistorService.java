package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.service.outbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.strancice.ttkacik.productbundlemanagementservice.domain.event.DomainEvent;
import cz.strancice.ttkacik.productbundlemanagementservice.domain.event.DomainEventPublisher;
import cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.adapter.out.persistence.OutboxEventEntity;
import cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.persistence.OutboxEventJpaRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class OutboxPersistorService implements Consumer<DomainEvent> {

    private final DomainEventPublisher domainEventPublisher;
    private final OutboxEventJpaRepository outboxEventRepository;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        domainEventPublisher.subscribe(this);
    }

    @Override
    public void accept(DomainEvent event) {
        try {
            String jsonData = objectMapper.writeValueAsString(event);
            OutboxEventEntity outboxEvent = new OutboxEventEntity();
            outboxEvent.setDomainEventType(event.getType());
            outboxEvent.setJsonData(jsonData);
            outboxEventRepository.save(outboxEvent);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
