package cz.strancice.ttkacik.accountmanagementservice.infrastructure.adapter.in.web;

import cz.strancice.ttkacik.accountmanagementservice.domain.BankAccount;
import org.mapstruct.Mapper;
import cz.strancice.ttkacik.accountmanagementservice.rest.model.BankAccountDTO;

@Mapper(componentModel = "spring")
public interface BankAccountDtoMapper {

    BankAccountDTO toDto(BankAccount entity);

    BankAccount toDomain(BankAccountDTO dto);
}
