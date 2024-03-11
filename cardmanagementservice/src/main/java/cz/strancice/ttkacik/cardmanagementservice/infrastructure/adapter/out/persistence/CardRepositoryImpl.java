package cz.strancice.ttkacik.cardmanagementservice.infrastructure.adapter.out.persistence;

import org.springframework.stereotype.Component;
import cz.strancice.ttkacik.cardmanagementservice.application.port.out.CardRepository;
import cz.strancice.ttkacik.cardmanagementservice.domain.Card;
import cz.strancice.ttkacik.cardmanagementservice.infrastructure.persistence.CardJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CardRepositoryImpl implements CardRepository {

    private final CardJpaRepository jpaRepository;
    private final CardMapper mapper;

    public CardRepositoryImpl(CardJpaRepository jpaRepository, CardMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Card> loadCard(String accountId) {
        return jpaRepository.findById(accountId).map(mapper::toDomain);
    }

    @Override
    public void saveCard(Card card) {
        CardEntity entity = mapper.toEntity(card);
        jpaRepository.save(entity);
    }

    @Override
    public void deleteCard(Card card) {
        jpaRepository.deleteById(card.getId());
    }

    @Override
    public String getCardStatusByCardId(String accountId) {
        return jpaRepository.findStatusById(accountId);
    }

    @Override
    public List<Card> getReferencedProductCardsOfType(String referencedProductId, String cardType) {
        return jpaRepository.findByReferencedProductIdAndCardType(referencedProductId, cardType)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Card> getReferencedProductCards(String referencedProductId) {
        return jpaRepository.findAllByReferencedProductId(referencedProductId)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
