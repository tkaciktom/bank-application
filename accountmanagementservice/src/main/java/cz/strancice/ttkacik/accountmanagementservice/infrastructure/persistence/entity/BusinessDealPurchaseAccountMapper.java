package cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BusinessDealPurchaseAccountMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "ownerId", source = "ownerId")
    cz.strancice.ttkacik.accountmanagementservice.domain.BusinessDealPurchaseAccount toDomain(BusinessDealPurchaseAccount entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "ownerId", source = "ownerId")
    BusinessDealPurchaseAccount toEntity(cz.strancice.ttkacik.accountmanagementservice.domain.BusinessDealPurchaseAccount domain);
}

