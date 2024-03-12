package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.persistence;

import cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.adapter.out.persistence.ProcessedMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedMessagesRepository extends JpaRepository<ProcessedMessageEntity, Long> {
    boolean existsByMessageId(String messageId);
}
