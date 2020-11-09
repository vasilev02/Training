package com.example.demo.services.impl;

import com.example.demo.entities.Account;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;
import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal amount, Long id) throws OperationNotSupportedException {

        Account account = accountRepository.findAccountById(id);

        if(account == null){
            throw new OperationNotSupportedException("User not exist !");
        }

        if(account.getBalance().compareTo(amount) < 0){
            throw new OperationNotSupportedException("Not enough money to withdraw !");
        }

        account.setBalance(account.getBalance().subtract(amount));

        accountRepository.saveAndFlush(account);

    }

    @Override
    public void transferMoney(BigDecimal amount, Long id) throws OperationNotSupportedException {

        Account account = accountRepository.findAccountById(id);

        if(account == null){
            throw new OperationNotSupportedException("User not exist !");
        }

        if(amount.compareTo(BigDecimal.valueOf(0)) < 0){
            throw new OperationNotSupportedException("Amount is negative !");
        }

        if(account.getBalance().compareTo(amount) < 0){
            throw new OperationNotSupportedException("Not enough money to withdraw !");
        }

        account.setBalance(account.getBalance().add(amount));

        accountRepository.saveAndFlush(account);


    }
}
