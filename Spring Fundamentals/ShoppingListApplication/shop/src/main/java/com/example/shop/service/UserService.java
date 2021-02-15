package com.example.shop.service;

import com.example.shop.model.service.UserLoginServiceModel;
import com.example.shop.model.service.UserRegisterServiceModel;

public interface UserService {
    UserRegisterServiceModel registerUser(UserRegisterServiceModel serviceModel);

    boolean checkUser(UserLoginServiceModel serviceModel);

    boolean checkIfUsernameAlreadyExists(String username);

    boolean checkIfEmailAlreadyExists(String email);

    boolean checkUserPassword(UserLoginServiceModel serviceModel);
}
