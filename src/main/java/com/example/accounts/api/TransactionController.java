package com.example.accounts.api;

import com.example.accounts.api.model.ApiResponse;
import com.example.accounts.api.model.TransactionFilter;
import com.example.accounts.api.model.TransactionResponse;
import com.example.accounts.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers/{customerNumber}/accounts/{accountNumber}/transactions")
@RequiredArgsConstructor
@Validated
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping()
    public ApiResponse<List<TransactionResponse>> getCustomerTransactions(@Valid TransactionFilter transactionFilter) {
        List<TransactionResponse> transactions = transactionService.findTransactions(transactionFilter);

        return ApiResponse.<List<TransactionResponse>>builder()
                .data(transactions)
                .build();
    }
}
