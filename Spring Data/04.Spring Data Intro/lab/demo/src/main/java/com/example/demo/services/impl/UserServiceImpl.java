package com.example.demo.services.impl;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) throws OperationNotSupportedException {

        User foundUser = this.userRepository.getByUsername(user.getUsername());

        if(foundUser == null){
            throw new OperationNotSupportedException("User exists !");
        }

        this.userRepository.saveAndFlush(foundUser);

    }
}
