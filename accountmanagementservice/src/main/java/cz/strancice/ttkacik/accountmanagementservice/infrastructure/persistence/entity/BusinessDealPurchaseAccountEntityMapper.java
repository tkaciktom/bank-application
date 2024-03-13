package cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence.entity;

import cz.strancice.ttkacik.accountmanagementservice.domain.BusinessDealPurchaseAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BusinessDealPurchaseAccountEntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "businessDealPurchaseId", source = "businessDealPurchaseId")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "ownerId", source = "ownerId")
    BusinessDealPurchaseAccount toDomain(BusinessDealPurchaseAccountEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "businessDealPurchaseId", source = "businessDealPurchaseId")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "ownerId", source = "ownerId")
    BusinessDealPurchaseAccountEntity toEntity(BusinessDealPurchaseAccount domain);
}

