package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.adapter.out.persistence;

import cz.strancice.ttkacik.productbundlemanagementservice.domain.BundleProposition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BundlePropositionEntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "benefits", source = "benefits")
    @Mapping(target = "proposedProductConfigurations", source = "proposedProductConfigurations")
    BundleProposition toDomain(BundlePropositionEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "benefits", source = "benefits")
    @Mapping(target = "proposedProductConfigurations", source = "proposedProductConfigurations")
    BundlePropositionEntity toEntity(BundleProposition entity);

}
