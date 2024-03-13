package cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence;

import cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence.entity.BusinessDealPurchaseAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessDealPurchaseAccountJpaRepository extends JpaRepository<BusinessDealPurchaseAccountEntity, String> {

    Optional<BusinessDealPurchaseAccountEntity> findByBusinessDealPurchaseId(String businessDealPurchaseId);

    Optional<BusinessDealPurchaseAccountEntity> findByBusinessDealPurchaseAccountId(String accountId);

}
