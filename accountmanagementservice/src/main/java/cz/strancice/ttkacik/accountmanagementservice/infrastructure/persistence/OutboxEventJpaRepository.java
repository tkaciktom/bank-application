package cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence;

import cz.strancice.ttkacik.accountmanagementservice.infrastructure.adapter.out.persistence.OutboxEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxEventJpaRepository extends JpaRepository<OutboxEventEntity, Long> {
}
