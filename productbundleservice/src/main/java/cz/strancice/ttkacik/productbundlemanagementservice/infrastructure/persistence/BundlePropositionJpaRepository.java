package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.persistence;

import cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.adapter.out.persistence.BundlePropositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BundlePropositionJpaRepository extends JpaRepository<BundlePropositionEntity, String> {
}
