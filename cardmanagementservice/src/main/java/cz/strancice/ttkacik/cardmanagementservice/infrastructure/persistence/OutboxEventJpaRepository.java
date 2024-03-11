package cz.strancice.ttkacik.cardmanagementservice.infrastructure.persistence;

import cz.strancice.ttkacik.cardmanagementservice.infrastructure.adapter.out.persistence.OutboxEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxEventJpaRepository extends JpaRepository<OutboxEventEntity, Long> {
}
