package com.example.demo.account.mapper;

import com.example.demo.account.dto.CreateAccountDto;
import com.example.demo.account.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    
    Account toEntity(CreateAccountDto createAccountDto);
}
