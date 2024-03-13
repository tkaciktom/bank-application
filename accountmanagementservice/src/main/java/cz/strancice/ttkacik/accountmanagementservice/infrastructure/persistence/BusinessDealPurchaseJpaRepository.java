package cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence;

import cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence.entity.BusinessDealPurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessDealPurchaseJpaRepository extends JpaRepository<BusinessDealPurchaseEntity, String> {

    Optional<BusinessDealPurchaseEntity> findById(String id);

}
