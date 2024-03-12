package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.adapter.out.persistence;

import cz.strancice.ttkacik.productbundlemanagementservice.domain.BusinessDealPurchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BusinessDealPurchaseEntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "ownerId", source = "ownerId")
    @Mapping(target = "proposition", source = "proposition")
    @Mapping(target = "activationStartDate", source = "activationStartDate")
    @Mapping(target = "activationEndDate", source = "activationEndDate")
    BusinessDealPurchase toDomain(BusinessDealPurchaseEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "ownerId", source = "ownerId")
    @Mapping(target = "proposition", source = "proposition")
    @Mapping(target = "activationStartDate", source = "activationStartDate")
    @Mapping(target = "activationEndDate", source = "activationEndDate")
    BusinessDealPurchaseEntity toEntity(BusinessDealPurchase entity);

}
