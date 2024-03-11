package cz.strancice.ttkacik.cardmanagementservice.infrastructure.adapter.in.web;

import cz.strancice.ttkacik.cardmanagementservice.application.service.CardCommandService;
import cz.strancice.ttkacik.cardmanagementservice.rest.model.CardDto;
import cz.strancice.ttkacik.cardmanagementservice.rest.api.CardsApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CardQueryAdapter implements CardsApi {

    private final CardCommandService cardCommandService;
    private final CardDtoMapper mapper;

    @Override
    public ResponseEntity<List<CardDto>> getReferencedProductCards(String referencedProductId) {
        var cardsDto = cardCommandService.getReferencedProductCards(referencedProductId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cardsDto);
    }

    @Override
    public ResponseEntity<List<CardDto>> getReferencedProductCardsOfType(String referencedProductId, String cardType) {
        var cardsDto = cardCommandService.getReferencedProductCardsOfType(referencedProductId, cardType)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cardsDto);
    }

    @Override
    public ResponseEntity<String> getCardStatus(String cardId) {
        var status = cardCommandService.getCardStatus(cardId);
        return ResponseEntity.ok(status);
    }
}
