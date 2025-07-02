package com.example.demo.account.controller;


import com.example.demo.account.dto.CreateAccountDto;
import com.example.demo.account.entity.Account;
import com.example.demo.account.service.AccountService;
import com.example.demo.tutorial.payload.ApiResponseBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@Tag(name = "Accounts", description = "Account management endpoints")
public class AccountController {
    private final AccountService accountService;
    
    AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    
    
    @PostMapping()
    @Operation(summary = "Creates a new user account")
    public ApiResponseBody<Account> createAccount(
            @Valid @RequestBody CreateAccountDto createAccountDto
    ) {
        Account newAccount = this.accountService.create(createAccountDto);
        return new ApiResponseBody<>(newAccount);
    }
    
    @GetMapping()
    @Operation(summary = "Get list of all user accounts")
    public ApiResponseBody<List<Account>> findAll() {
        return new ApiResponseBody<>(accountService.findAll());
    }
    
}
