package cz.strancice.ttkacik.cardmanagementservice.application.port.out;

import cz.strancice.ttkacik.cardmanagementservice.domain.Card;

import java.util.List;
import java.util.Optional;

public interface CardRepository {
    Optional<Card> loadCard(String cardId);
    void saveCard(Card card);
    void deleteCard(Card card);
    String getCardStatusByCardId(String cardId);

    List<Card> getReferencedProductCardsOfType(String referencedProductId, String cardType);

    List<Card> getReferencedProductCards(String referencedProductId);
}
