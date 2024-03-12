package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.adapter.out.persistence;

import cz.strancice.ttkacik.productbundlemanagementservice.application.port.out.BusinessDealPurchaseRepository;
import cz.strancice.ttkacik.productbundlemanagementservice.domain.BusinessDealPurchase;
import cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.persistence.BusinessDealPurchaseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BusinessDealPurchaseRepositoryImpl implements BusinessDealPurchaseRepository {

    private final BusinessDealPurchaseJpaRepository jpaRepository;
    private final BusinessDealPurchaseEntityMapper mapper;

    @Override
    public Optional<BusinessDealPurchase> getBusinessDealPurchaseById(String id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<BusinessDealPurchase> getBusinessDealPurchaseByOwnerId(String ownerId) {
        return jpaRepository.findByOwnerId(ownerId)
                .map(mapper::toDomain);
    }

    @Override
    public void save(BusinessDealPurchase businessDealPurchase) {
        jpaRepository.save(mapper.toEntity(businessDealPurchase));
    }

}
