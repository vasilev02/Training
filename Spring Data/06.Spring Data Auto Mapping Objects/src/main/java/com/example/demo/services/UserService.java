package com.example.demo.services;

import com.example.demo.dto.LoginUserDto;
import com.example.demo.dto.RegisterUserDto;

public interface UserService {

    String registerUser(RegisterUserDto registerUserDto);

    String loginUser(LoginUserDto loginUserDto);

    String logout();

    String getLoggedUser();

}
