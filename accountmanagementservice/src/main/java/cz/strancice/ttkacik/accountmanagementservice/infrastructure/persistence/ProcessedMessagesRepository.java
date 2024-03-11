package cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence;

import cz.strancice.ttkacik.accountmanagementservice.infrastructure.adapter.out.persistence.ProcessedMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedMessagesRepository extends JpaRepository<ProcessedMessageEntity, Long> {
    boolean existsByMessageId(String messageId);
}
