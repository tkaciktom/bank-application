package cz.strancice.ttkacik.cardmanagementservice.domain.exception;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String message) {
        super(message);
    }
}
