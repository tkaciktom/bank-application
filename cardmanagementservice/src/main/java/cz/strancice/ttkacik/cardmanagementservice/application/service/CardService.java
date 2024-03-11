package cz.strancice.ttkacik.cardmanagementservice.application.service;

import cz.strancice.ttkacik.cardmanagementservice.application.port.in.*;
import cz.strancice.ttkacik.cardmanagementservice.application.port.out.CardRepository;
import cz.strancice.ttkacik.cardmanagementservice.domain.Card;
import cz.strancice.ttkacik.cardmanagementservice.infrastructure.service.idempotency.IdempotentConsumer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cz.strancice.ttkacik.cardmanagementservice.domain.exception.CardNotFoundException;

import java.util.List;

@Service
@Transactional
public class CardService implements
        IssueCardUseCase,
        RemoveCardUseCase,
        GetCardStatusUseCase,
        GetReferencedProductCardsUseCase,
        GetReferencedProductCardsOfTypeUseCase {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    @Transactional
    @IdempotentConsumer(messageParamName = "messageId")
    public Card issueCard(String referencedProductId, String cardType, String cardVariant, String messageId) {
        Card newCard = new Card(referencedProductId, cardType, cardVariant);
        newCard.open();
        cardRepository.saveCard(newCard);
        return newCard;
    }

    @Override
    @Transactional
    @IdempotentConsumer(messageParamName = "messageId")
    public void removeCard(String cardId, String reasonType, String messageId) {
        Card card = cardRepository.loadCard(cardId)
                .orElseThrow(() -> new CardNotFoundException("Card not found for id: " + cardId));
        card.close(reasonType);
        cardRepository.deleteCard(card);
    }

    @Override
    public String getCardStatus(String cardId) {
        return cardRepository.getCardStatusByCardId(cardId);
    }

    @Override
    public List<Card> getReferencedProductCards(String referencedProductId) {
        return cardRepository.getReferencedProductCards(referencedProductId);
    }

    @Override
    public List<Card> getReferencedProductCardsOfType(String referencedProductId, String accountType) {
        return cardRepository.getReferencedProductCardsOfType(referencedProductId, accountType);
    }
}
