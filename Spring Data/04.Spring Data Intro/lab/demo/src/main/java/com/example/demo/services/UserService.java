package com.example.demo.services;

import org.springframework.stereotype.Service;
import com.example.demo.entities.User;


import javax.naming.OperationNotSupportedException;


public interface UserService {

    public void registerUser(User user) throws OperationNotSupportedException;

}
