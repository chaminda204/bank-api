package com.example.accounts.service.mapper;

import com.example.accounts.api.model.AccountResponse;
import com.example.accounts.service.domain.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    List<AccountResponse> map(List<Account> accounts);

    @Mappings({
            @Mapping(source = "accountNumber", target = "accountNumber")
    })
    AccountResponse map(Account account);

}
