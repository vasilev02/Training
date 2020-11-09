package com.example.demo.services;

import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;
import java.math.BigDecimal;

public interface AccountService {

     public void withdrawMoney(BigDecimal amount, Long id) throws OperationNotSupportedException;
     public void transferMoney(BigDecimal amount, Long id) throws OperationNotSupportedException;

}
