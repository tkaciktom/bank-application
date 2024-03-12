package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.persistence;

import cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.adapter.out.persistence.BundleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BundleJpaRepository extends JpaRepository<BundleEntity, String> {

    public List<BundleEntity> findBundleEntitiesByOwnerId(String ownerId);

}
