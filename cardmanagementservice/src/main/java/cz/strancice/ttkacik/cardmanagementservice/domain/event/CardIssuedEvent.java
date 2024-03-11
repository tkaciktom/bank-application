package cz.strancice.ttkacik.cardmanagementservice.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardIssuedEvent implements DomainEvent {
    private String cardId;
    private String referencedProductId;
    private String cardType;
    private String cardVariant;

    @Override
    public Type getType() {
        return Type.CARD_ISSUED;
    }
}
