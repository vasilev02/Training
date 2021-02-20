package com.example.andreys.service;

import com.example.andreys.model.service.UserRegisterServiceModel;

public interface UserService {
    UserRegisterServiceModel registerUser(UserRegisterServiceModel serviceModel);

    boolean containsUsername(String username);

    boolean containsEmail(String email);

    boolean authenticateUser(String username, String password);
}
