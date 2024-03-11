package cz.strancice.ttkacik.accountmanagementservice.infrastructure.service.outbox;

import cz.strancice.ttkacik.accountmanagementservice.domain.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OutboxEvent {
    private Long id;
    private DomainEvent.Type domainEventType;
    private DomainEvent event;
}
