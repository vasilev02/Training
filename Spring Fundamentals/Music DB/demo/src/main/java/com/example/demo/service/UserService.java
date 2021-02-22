package com.example.demo.service;

import com.example.demo.model.entity.User;
import com.example.demo.model.service.UserRegisterServiceModel;

import java.util.Optional;

public interface UserService {
    UserRegisterServiceModel registerUser(UserRegisterServiceModel serviceModel);

    boolean authenticateUser(String username, String password);

    User findUserByUsername(String username);
}
