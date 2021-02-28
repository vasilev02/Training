package com.softuni.judge.service;


import com.softuni.judge.model.entity.User;
import com.softuni.judge.model.service.UserProfileServiceModel;
import com.softuni.judge.model.service.UserServiceModel;

import java.util.List;

public interface UserService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel getUserByUsernameAndPassword(String username, String password);

    void login(UserServiceModel userServiceModel);

    void logout();

    List<String> getUsernames();

    void changeRole(String username, String role);

    User getUserByUsername(String name);

    UserProfileServiceModel getUserInfo();

}
