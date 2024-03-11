package cz.strancice.ttkacik.cardmanagementservice.application.port.in;

import cz.strancice.ttkacik.cardmanagementservice.domain.Card;

public interface IssueCardUseCase {
    Card issueCard(String referencedProductId, String cardType, String cardVariant, String messageId);
}
