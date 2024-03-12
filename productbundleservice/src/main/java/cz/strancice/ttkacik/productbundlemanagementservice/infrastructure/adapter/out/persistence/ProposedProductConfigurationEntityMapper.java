package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.adapter.out.persistence;

import cz.strancice.ttkacik.productbundlemanagementservice.domain.ProposedProductConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProposedProductConfigurationEntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "maxCount", source = "maxCount")
    ProposedProductConfiguration toDomain(ProposedProductConfigurationEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "maxCount", source = "maxCount")
    ProposedProductConfigurationEntity toEntity(ProposedProductConfiguration entity);

}
