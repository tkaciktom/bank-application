package cz.strancice.ttkacik.accountmanagementservice.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountClosedEvent implements DomainEvent {
    private String accountId;
    private String userId;
    private String accountType;
    private String reasonType;

    @Override
    public Type getType() {
        return Type.ACCOUNT_CLOSED;
    }
}
