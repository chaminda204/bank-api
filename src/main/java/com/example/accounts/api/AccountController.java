package com.example.accounts.api;

import com.example.accounts.api.model.AccountResponse;
import com.example.accounts.api.model.ApiResponse;
import com.example.accounts.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("/customers/{customerId}/accounts")
@RequiredArgsConstructor
@Validated
public class AccountController {

    private final AccountService accountService;

    @GetMapping()
    public ApiResponse<List<AccountResponse>> getByAccountId(@PathVariable(value = "customerId")
                                                             @Pattern(regexp = "^[0-9]{9}$", message = "Invalid customerNumber")
                                                                     String customerId) {
        return ApiResponse.<List<AccountResponse>>builder()
                .data(accountService.findAccountsByCustomerNumber(customerId))
                .build();
    }
}
