package com.example.heroes.service;

import com.example.heroes.model.service.UserLoginServiceModel;
import com.example.heroes.model.service.UserRegisterServiceModel;

public interface UserService {
    UserRegisterServiceModel registerUser(UserRegisterServiceModel serviceModel);

    boolean checkUsernameExistence(String username);

    boolean checkEmailExistence(String email);

    boolean checkUsernameExistenceLogin(String username);

    boolean checkPassword(UserLoginServiceModel serviceModel);
}
