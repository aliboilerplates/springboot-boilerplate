package com.example.demo.account.service;

import com.example.demo.account.dto.CreateAccountDto;
import com.example.demo.account.entity.Account;
import com.example.demo.account.mapper.AccountMapper;
import com.example.demo.account.repository.AccountRepository;
import com.example.demo.shared.classes.BaseService;
import com.example.demo.shared.exception.PostgresErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService extends BaseService<Account, UUID> {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final static Logger logger = LoggerFactory.getLogger(AccountService.class);
    
    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        super(accountRepository);
        this.accountMapper = accountMapper;
        this.accountRepository = accountRepository;
    }
    
    
    public Account create(CreateAccountDto createAccountDto) {
        Account account = this.accountMapper.toEntity(createAccountDto);
        try {
            return this.accountRepository.save(account);
        } catch (DbActionExecutionException ex) {
            logger.error("Failed to create account, {}", ex.getMessage(), ex);
            throw PostgresErrorHandler.mapToHttpException(ex);
        }
        
    }
    
}
