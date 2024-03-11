package cz.strancice.ttkacik.cardmanagementservice.infrastructure.adapter.out.persistence;

import cz.strancice.ttkacik.cardmanagementservice.domain.event.DomainEvent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OutboxEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DomainEvent.Type domainEventType;
    private String jsonData;

}
