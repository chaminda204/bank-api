package com.example.accounts.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name="account_seq", sequenceName = "account_seq", allocationSize=50)
    private Long accountNumber;

    private Long customerNumber;

    private String accountName;

    private String accountType;

    private String currency;

    private LocalDate balanceDate;

    private BigDecimal openingBalance;
}
