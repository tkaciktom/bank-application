package cz.strancice.ttkacik.cardmanagementservice.infrastructure.adapter.in.web;

import cz.strancice.ttkacik.cardmanagementservice.domain.Card;
import org.mapstruct.Mapper;
import cz.strancice.ttkacik.cardmanagementservice.rest.model.CardDto;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CardDtoMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "referencedProductId", source = "referencedProductId")
    @Mapping(target = "cardNumber", source = "cardNumber")
    @Mapping(target = "cardType", source = "cardType")
    @Mapping(target = "cardVariant", source = "cardVariant")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "reasonClosedType", source = "reasonClosedType")
    CardDto toDto(Card entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "referencedProductId", source = "referencedProductId")
    @Mapping(target = "cardNumber", source = "cardNumber")
    @Mapping(target = "cardType", source = "cardType")
    @Mapping(target = "cardVariant", source = "cardVariant")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "reasonClosedType", source = "reasonClosedType")
    Card toDomain(CardDto dto);
}
