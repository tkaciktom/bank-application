package cz.strancice.ttkacik.cardmanagementservice.application.port.in;

import cz.strancice.ttkacik.cardmanagementservice.domain.Card;

import java.util.List;

public interface GetReferencedProductCardsOfTypeUseCase {

    List<Card> getReferencedProductCardsOfType(String referencedProductId, String cardType);

}
