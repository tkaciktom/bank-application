package cz.strancice.ttkacik.accountmanagementservice.infrastructure.adapter.out.persistence;

import cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence.entity.BankAccountEntity;
import cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence.entity.BankAccountEntityMapper;
import org.springframework.stereotype.Component;
import cz.strancice.ttkacik.accountmanagementservice.application.port.out.AccountRepository;
import cz.strancice.ttkacik.accountmanagementservice.domain.BankAccount;
import cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence.BankAccountJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BankAccountRepositoryImpl implements AccountRepository {

    private final BankAccountJpaRepository jpaRepository;
    private final BankAccountEntityMapper mapper;

    public BankAccountRepositoryImpl(BankAccountJpaRepository jpaRepository, BankAccountEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<BankAccount> loadAccount(String accountId) {
        return jpaRepository.findById(accountId).map(mapper::toDomain);
    }

    @Override
    public void saveAccount(BankAccount bankAccount) {
        BankAccountEntity entity = mapper.toEntity(bankAccount);
        jpaRepository.save(entity);
    }

    @Override
    public void deleteAccount(BankAccount bankAccount) {
        jpaRepository.deleteById(bankAccount.getId());
    }

    @Override
    public String getAccountStatusByAccountId(String accountId) {
        return jpaRepository.findStatusById(accountId);
    }

    @Override
    public List<BankAccount> getUserAccountsOfType(String userId, String accountType) {
        return jpaRepository.findByUserIdAndAccountType(userId, accountType)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<BankAccount> getUserAccounts(String userId) {
        return jpaRepository.findAllByUserId(userId)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
