package com.example.accounts.service;

import com.example.accounts.api.model.TransactionFilter;
import com.example.accounts.api.model.TransactionResponse;
import com.example.accounts.exceptions.ResourceNotFoundException;
import com.example.accounts.repository.TransactionRepository;
import com.example.accounts.service.domain.Transaction;
import com.example.accounts.service.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    public List<TransactionResponse> findTransactions(TransactionFilter transactionFilter) {
        List<Transaction> transactions = transactionRepository
                .findByCustomerNumberAndAccountNumber(transactionFilter.getCustomerNumber(), transactionFilter.getAccountNumber());
        if (transactions == null || transactions.isEmpty()) {
            throw new ResourceNotFoundException("NOT_FOUND", "Resource Not Found");
        }
        return transactionMapper.map(transactions);
    }

}
