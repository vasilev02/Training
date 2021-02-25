package com.example.gira.service;

import com.example.gira.model.entity.User;
import com.example.gira.model.service.UserRegisterServiceModel;

public interface UserService {
    UserRegisterServiceModel registerUser(UserRegisterServiceModel serviceModel);

    boolean checkUsernameIfExists(String username);

    boolean checkEmailIfExists(String email);

    boolean authenticateUser(String email, String password);

    User getUserByEmail(String email);
}
