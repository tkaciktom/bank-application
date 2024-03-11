package cz.strancice.ttkacik.cardmanagementservice.infrastructure.persistence;

import cz.strancice.ttkacik.cardmanagementservice.infrastructure.adapter.out.persistence.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardJpaRepository extends JpaRepository<CardEntity, String> {

    String findStatusById(String id);

    List<CardEntity> findByReferencedProductIdAndCardType(String referencedProductId, String cardType);

    List<CardEntity> findAllByReferencedProductId(String userId);

}
