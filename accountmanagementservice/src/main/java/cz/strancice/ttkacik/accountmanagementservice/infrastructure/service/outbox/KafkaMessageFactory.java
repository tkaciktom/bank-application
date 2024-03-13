package cz.strancice.ttkacik.accountmanagementservice.infrastructure.service.outbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.strancice.ttkacik.accountmanagementservice.domain.event.BankAccountClosedEvent;
import cz.strancice.ttkacik.accountmanagementservice.domain.event.BankAccountOpenedEvent;
import cz.strancice.ttkacik.accountmanagementservice.domain.event.DomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import cz.strancice.ttkacik.bank.accountmanagement.events.*;

@Component
@RequiredArgsConstructor
public class KafkaMessageFactory {

    private final ObjectMapper objectMapper;

    public Object toMessage(DomainEvent.Type domainEventType, String jsonData) {
        switch (domainEventType) {
            case ACCOUNT_OPENED: return toAccountOpenedMessage(jsonData);
            case ACCOUNT_CLOSED: return toAccountClosedMessage(jsonData);
            default: throw new IllegalArgumentException("Unknown domain event type %s".formatted(domainEventType));
        }
    }

    private Object toAccountOpenedMessage(String jsonData) {
        try {
            var event = objectMapper.readValue(jsonData, BankAccountOpenedEvent.class);
            return new AccountOpenedEvent(
                    event.getUserId(),
                    event.getAccountId(),
                    event.getAccountType(),
                    event.getBusinessDealPurchaseId(),
                    event.getBusinessDealPurchaseAccountId()
            );
        } catch (Exception e) {
            throw new RuntimeException("Cannot read json data", e);
        }
    }

    private Object toAccountClosedMessage(String jsonData) {
        try {
            var event = objectMapper.readValue(jsonData, BankAccountClosedEvent.class);
            return new AccountClosedEvent(event.getUserId(), event.getAccountId(), event.getAccountType(), event.getReasonType());
        } catch (Exception e) {
            throw new RuntimeException("Cannot read json data", e);
        }
    }

}
