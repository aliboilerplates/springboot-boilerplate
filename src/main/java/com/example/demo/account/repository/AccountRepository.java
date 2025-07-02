package com.example.demo.account.repository;

import com.example.demo.account.entity.Account;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;


public interface AccountRepository extends ListCrudRepository<Account, UUID> {
}
