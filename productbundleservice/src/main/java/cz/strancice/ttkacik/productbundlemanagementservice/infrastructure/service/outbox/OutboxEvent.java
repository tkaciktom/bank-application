package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.service.outbox;

import cz.strancice.ttkacik.productbundlemanagementservice.domain.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OutboxEvent {
    private Long id;
    private DomainEvent.Type domainEventType;
    private DomainEvent event;
}
