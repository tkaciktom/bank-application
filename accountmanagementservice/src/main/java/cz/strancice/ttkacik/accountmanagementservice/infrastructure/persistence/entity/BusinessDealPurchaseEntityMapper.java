package cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence.entity;

import cz.strancice.ttkacik.accountmanagementservice.domain.BusinessDealPurchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BusinessDealPurchaseEntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "accounts", source = "accounts")
    BusinessDealPurchase toDomain(BusinessDealPurchaseEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "accounts", source = "accounts")
    BusinessDealPurchaseEntity toEntity(BusinessDealPurchase domain);
}

