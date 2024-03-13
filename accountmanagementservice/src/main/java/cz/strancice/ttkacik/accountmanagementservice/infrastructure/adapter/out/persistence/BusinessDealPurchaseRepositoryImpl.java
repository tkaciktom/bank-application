package cz.strancice.ttkacik.accountmanagementservice.infrastructure.adapter.out.persistence;

import cz.strancice.ttkacik.accountmanagementservice.application.port.out.BusinessDealPurchaseRepository;
import cz.strancice.ttkacik.accountmanagementservice.domain.BusinessDealPurchase;
import cz.strancice.ttkacik.accountmanagementservice.domain.BusinessDealPurchaseAccount;
import cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence.BusinessDealPurchaseJpaRepository;
import cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence.entity.BusinessDealPurchaseEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusinessDealPurchaseRepositoryImpl implements BusinessDealPurchaseRepository {

    private final BusinessDealPurchaseJpaRepository jpaRepository;
    private final BusinessDealPurchaseEntityMapper mapper;


    @Override
    public Optional<BusinessDealPurchase> loadById(String id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public void save(BusinessDealPurchase purchase) {
        jpaRepository.save(mapper.toEntity(purchase));
    }

    @Override
    public void remove(BusinessDealPurchase purchase) {
        jpaRepository.delete(mapper.toEntity(purchase));
    }
}
