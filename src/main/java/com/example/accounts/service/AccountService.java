package com.example.accounts.service;

import com.example.accounts.api.model.AccountResponse;
import com.example.accounts.exceptions.ResourceNotFoundException;
import com.example.accounts.repository.AccountRepository;
import com.example.accounts.service.domain.Account;
import com.example.accounts.service.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Long.valueOf;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;


    public List<AccountResponse> findAccountsByCustomerNumber(String customerNumber) {
        List<Account> accounts = accountRepository.findByCustomerNumber(valueOf(customerNumber));
        if (accounts == null || accounts.isEmpty()) {
            throw new ResourceNotFoundException("NOT_FOUND", "Resource Not Found");
        }
        return accountMapper.map(accounts);

    }
}
