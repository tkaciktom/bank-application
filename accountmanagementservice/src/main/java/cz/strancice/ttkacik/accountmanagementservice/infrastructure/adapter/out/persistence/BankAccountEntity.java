package cz.strancice.ttkacik.accountmanagementservice.infrastructure.adapter.out.persistence;

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
    private BigDecimal balance;
    private String status;
    private String reasonClosedType;

}
