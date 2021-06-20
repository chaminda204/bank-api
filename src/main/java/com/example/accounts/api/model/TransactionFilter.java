package com.example.accounts.api.model;

import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;

import static java.lang.Long.valueOf;

@Setter
@Validated
public class TransactionFilter {

    @Pattern(regexp = "^[0-9]{9}$", message = "Invalid customerNumber")
    private String customerNumber;

    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid accountNumber")
    private String accountNumber;

    public Long getCustomerNumber() {
        return valueOf(customerNumber);
    }

    public Long getAccountNumber() {
        return valueOf(accountNumber);
    }

}
