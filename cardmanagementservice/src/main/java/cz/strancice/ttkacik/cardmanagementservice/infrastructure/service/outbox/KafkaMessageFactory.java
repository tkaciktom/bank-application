package cz.strancice.ttkacik.cardmanagementservice.infrastructure.service.outbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.strancice.ttkacik.cardmanagementservice.domain.event.DomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import cz.strancice.ttkacik.bank.cardmanagement.events.*;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class KafkaMessageFactory {

    private final ObjectMapper objectMapper;

    public Object toMessage(DomainEvent.Type domainEventType, String jsonData) {
        return switch (domainEventType) {
            case CARD_ISSUED -> toCardIssuedMessage(jsonData);
            case CARD_REMOVED -> toCardRemovedMessage(jsonData);
            default -> throw new IllegalArgumentException("Unknown domain event type %s".formatted(domainEventType));
        };
    }

    private Object toCardIssuedMessage(String jsonData) {
        try {
            var event = objectMapper.readValue(jsonData, cz.strancice.ttkacik.cardmanagementservice.domain.event.CardIssuedEvent.class);
            return new CardIssuedEvent(event.getReferencedProductId(), event.getCardId(), event.getCardType(), new Date().toGMTString());
        } catch (Exception e) {
            throw new RuntimeException("Cannot read json data", e);
        }
    }

    private Object toCardRemovedMessage(String jsonData) {
        try {
            var event = objectMapper.readValue(jsonData, cz.strancice.ttkacik.cardmanagementservice.domain.event.CardRemovedEvent.class);
            return new CardRemovedEvent(event.getReferencedProductId(), event.getCardId(), event.getCardType(), event.getReasonType());
        } catch (Exception e) {
            throw new RuntimeException("Cannot read json data", e);
        }
    }

}
