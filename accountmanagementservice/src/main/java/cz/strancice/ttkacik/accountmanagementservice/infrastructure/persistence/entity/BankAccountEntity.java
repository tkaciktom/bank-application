package cz.strancice.ttkacik.accountmanagementservice.infrastructure.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class BankAccountEntity {

    @Id
    private String id;
    private String userId;
    private String accountNumber;
    private String accountType;
    private String accountName;
    private BigDecimal balance;
    private String status;
    private String businessDealPurchaseId;
    private String businessDealPurchaseAccountId;
    private String reasonClosedType;

}
