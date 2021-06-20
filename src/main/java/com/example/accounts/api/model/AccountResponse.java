package com.example.accounts.api.model;

import com.example.accounts.api.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    private Long accountNumber;

    private Long customerNumber;

    private String accountName;

    private String accountType;

    private String currency;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate balanceDate;

    private BigDecimal openingBalance;

}
