package cz.strancice.ttkacik.cardmanagementservice.infrastructure.adapter.out.persistence;

import cz.strancice.ttkacik.cardmanagementservice.domain.CardState;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class CardEntity {

    @Id
    private String id;
    private String referencedProductId;
    private String cardNumber;
    private String cardType;
    private String cardVariant;
    private CardState status;
    private String reasonClosedType;

}
