package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.persistence;

import cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.adapter.out.persistence.BusinessDealPurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessDealPurchaseJpaRepository extends JpaRepository<BusinessDealPurchaseEntity, String> {

    Optional<BusinessDealPurchaseEntity> findByOwnerId(String ownerId);

}
