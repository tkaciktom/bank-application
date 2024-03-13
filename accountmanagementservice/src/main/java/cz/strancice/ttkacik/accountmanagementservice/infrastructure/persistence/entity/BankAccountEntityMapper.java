package cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import cz.strancice.ttkacik.accountmanagementservice.domain.BankAccount;

@Mapper(componentModel = "spring")
public interface BankAccountEntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "accountNumber", source = "accountNumber")
    @Mapping(target = "accountType", source = "accountType")
    @Mapping(target = "accountName", source = "accountName")
    @Mapping(target = "balance", source = "balance")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "reasonClosedType", source = "reasonClosedType")
    @Mapping(target = "businessDealPurchaseId", source = "businessDealPurchaseId")
    @Mapping(target = "businessDealPurchaseAccountId", source = "businessDealPurchaseAccountId")
    BankAccount toDomain(BankAccountEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "accountNumber", source = "accountNumber")
    @Mapping(target = "accountType", source = "accountType")
    @Mapping(target = "accountName", source = "accountName")
    @Mapping(target = "balance", source = "balance")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "reasonClosedType", source = "reasonClosedType")
    @Mapping(target = "businessDealPurchaseId", source = "businessDealPurchaseId")
    @Mapping(target = "businessDealPurchaseAccountId", source = "businessDealPurchaseAccountId")
    BankAccountEntity toEntity(BankAccount domain);
}

