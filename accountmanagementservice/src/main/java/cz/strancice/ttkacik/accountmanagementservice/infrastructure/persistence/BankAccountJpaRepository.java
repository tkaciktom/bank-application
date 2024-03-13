package cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence;

import cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountJpaRepository extends JpaRepository<BankAccountEntity, String> {

    String findStatusById(String id);

    List<BankAccountEntity> findByUserIdAndAccountType(String userId, String accountType);

    List<BankAccountEntity> findAllByUserId(String userId);

}
