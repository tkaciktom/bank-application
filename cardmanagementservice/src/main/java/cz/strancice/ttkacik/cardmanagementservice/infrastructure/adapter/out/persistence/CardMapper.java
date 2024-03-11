package cz.strancice.ttkacik.cardmanagementservice.infrastructure.adapter.out.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import cz.strancice.ttkacik.cardmanagementservice.domain.Card;

@Mapper(componentModel = "spring")
public interface CardMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "referencedProductId", source = "referencedProductId")
    @Mapping(target = "cardNumber", source = "cardNumber")
    @Mapping(target = "cardType", source = "cardType")
    @Mapping(target = "cardVariant", source = "cardVariant")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "reasonClosedType", source = "reasonClosedType")
    Card toDomain(CardEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "referencedProductId", source = "referencedProductId")
    @Mapping(target = "cardNumber", source = "cardNumber")
    @Mapping(target = "cardType", source = "cardType")
    @Mapping(target = "cardVariant", source = "cardVariant")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "reasonClosedType", source = "reasonClosedType")
    CardEntity toEntity(Card domain);
}

