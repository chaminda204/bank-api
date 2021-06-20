package com.example.accounts.service.mapper;

import com.example.accounts.api.model.TransactionResponse;
import com.example.accounts.service.domain.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    List<TransactionResponse> map(List<Transaction> transactions);

    @Mappings({
            @Mapping(source = "transactionId", target = "transactionId")
    })
    TransactionResponse map(Transaction transaction);
}
