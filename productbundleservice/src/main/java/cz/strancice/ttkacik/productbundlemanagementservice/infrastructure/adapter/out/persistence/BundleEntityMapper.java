package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.adapter.out.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import cz.strancice.ttkacik.productbundlemanagementservice.domain.Bundle;

@Mapper(componentModel = "spring")
public interface BundleEntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "ownerId", source = "ownerId")
    @Mapping(target = "businessDealPurchase", source = "businessDealPurchase")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "statusStateMachine", ignore = true)
    Bundle toDomain(BundleEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "ownerId", source = "ownerId")
    @Mapping(target = "businessDealPurchase", source = "businessDealPurchase")
    @Mapping(target = "status", source = "status")
    BundleEntity toEntity(Bundle domain);
}

