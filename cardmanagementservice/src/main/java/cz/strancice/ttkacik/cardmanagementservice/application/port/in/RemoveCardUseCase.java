package cz.strancice.ttkacik.cardmanagementservice.application.port.in;

public interface RemoveCardUseCase {
    void removeCard(String cardId, String reasonType, String messageId);
}
