package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.service.outbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.strancice.ttkacik.bank.bundlemanagement.BusinessDealPurchaseCreatedEvent;
import cz.strancice.ttkacik.productbundlemanagementservice.domain.event.BusinessDealPurchaseConfirmedEvent;
import cz.strancice.ttkacik.productbundlemanagementservice.domain.event.DomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaMessageFactory {

    private final ObjectMapper objectMapper;

    public Object toMessage(DomainEvent.Type domainEventType, String jsonData) {
        return switch (domainEventType) {
            case BUSINESS_DEAL_PURCHASE_CREATED -> toBusinessDealPurchaseCreatedEvent(jsonData);
            case BUSINESS_DEAL_PURCHASE_CONFIRMED -> toBusinessDealPurchaseConfirmedEvent(jsonData);
            default -> throw new IllegalArgumentException("Unknown domain event type %s".formatted(domainEventType));
        };
    }

    private Object toBusinessDealPurchaseCreatedEvent(String jsonData) {
        try {
            var event = objectMapper.readValue(jsonData, cz.strancice.ttkacik.productbundlemanagementservice.domain.event.BusinessDealPurchaseCreatedEvent.class);
            return new BusinessDealPurchaseCreatedEvent(event.getBusinessDealPurchaseId(), event.getBundlePropositionId(), event.getOwnerId());
        } catch (Exception e) {
            throw new RuntimeException("Cannot read json data", e);
        }
    }

    private Object toBusinessDealPurchaseConfirmedEvent(String jsonData) {
        try {
            var event = objectMapper.readValue(jsonData, cz.strancice.ttkacik.productbundlemanagementservice.domain.event.BusinessDealPurchaseConfirmedEvent.class);
            return new BusinessDealPurchaseConfirmedEvent(event.getBusinessDealPurchaseId(), event.getBundlePropositionId(), event.getOwnerId());
        } catch (Exception e) {
            throw new RuntimeException("Cannot read json data", e);
        }
    }

}
