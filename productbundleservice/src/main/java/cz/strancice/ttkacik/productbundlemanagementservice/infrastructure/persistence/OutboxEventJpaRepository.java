package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.persistence;

import cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.adapter.out.persistence.OutboxEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxEventJpaRepository extends JpaRepository<OutboxEventEntity, Long> {
}
