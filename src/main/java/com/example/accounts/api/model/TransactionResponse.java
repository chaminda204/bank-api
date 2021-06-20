package com.example.accounts.api.model;

import com.example.accounts.api.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@JsonInclude(NON_NULL)
public class TransactionResponse {

    private Long transactionId;

    private Long customerNumber;

    private Long accountNumber;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

    private BigDecimal credit;

    private BigDecimal debit;

    private String operation;

    private String transactionNarrative;
}
