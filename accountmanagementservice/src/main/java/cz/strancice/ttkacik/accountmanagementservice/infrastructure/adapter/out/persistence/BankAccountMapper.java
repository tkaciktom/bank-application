package cz.strancice.ttkacik.accountmanagementservice.infrastructure.adapter.out.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import cz.strancice.ttkacik.accountmanagementservice.domain.BankAccount;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "accountNumber", source = "accountNumber")
    @Mapping(target = "accountType", source = "accountType")
    @Mapping(target = "balance", source = "balance")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "reasonClosedType", source = "reasonClosedType")
    BankAccount toDomain(BankAccountEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "accountNumber", source = "accountNumber")
    @Mapping(target = "accountType", source = "accountType")
    @Mapping(target = "balance", source = "balance")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "reasonClosedType", source = "reasonClosedType")
    BankAccountEntity toEntity(BankAccount domain);
}

