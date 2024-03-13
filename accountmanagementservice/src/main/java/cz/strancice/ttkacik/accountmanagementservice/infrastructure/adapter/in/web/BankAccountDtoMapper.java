package cz.strancice.ttkacik.accountmanagementservice.infrastructure.adapter.in.web;

import cz.strancice.ttkacik.accountmanagementservice.domain.BankAccount;
import org.mapstruct.Mapper;
import cz.strancice.ttkacik.accountmanagementservice.rest.model.BankAccountDto;

@Mapper(componentModel = "spring")
public interface BankAccountDtoMapper {

    BankAccountDto toDto(BankAccount entity);

    BankAccount toDomain(BankAccountDto dto);
}
