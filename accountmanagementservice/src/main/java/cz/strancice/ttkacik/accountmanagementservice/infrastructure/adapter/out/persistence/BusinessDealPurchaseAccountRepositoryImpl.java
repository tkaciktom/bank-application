package cz.strancice.ttkacik.accountmanagementservice.infrastructure.adapter.out.persistence;

import cz.strancice.ttkacik.accountmanagementservice.application.port.out.BusinessDealPurchaseAccountRepository;
import cz.strancice.ttkacik.accountmanagementservice.domain.BusinessDealPurchaseAccount;
import cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence.entity.BusinessDealPurchaseAccountEntityMapper;
import cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence.BusinessDealPurchaseAccountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusinessDealPurchaseAccountRepositoryImpl implements BusinessDealPurchaseAccountRepository {

    private final BusinessDealPurchaseAccountJpaRepository jpaRepository;
    private final BusinessDealPurchaseAccountEntityMapper mapper;


    @Override
    public Optional<BusinessDealPurchaseAccount> loadAccount(String id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public void saveAccount(BusinessDealPurchaseAccount account) {
        jpaRepository.save(mapper.toEntity(account));
    }

    @Override
    public List<BusinessDealPurchaseAccount> getAccountsByBusinessDealPurchaseId(String businessDealPurchaseId) {
        return jpaRepository.findByBusinessDealPurchaseId(businessDealPurchaseId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BusinessDealPurchaseAccount> getAccountByBusinessDealPurchaseAccountId(String accountId) {
        return jpaRepository.findByBusinessDealPurchaseAccountId(accountId)
                .map(mapper::toDomain);
    }
}
