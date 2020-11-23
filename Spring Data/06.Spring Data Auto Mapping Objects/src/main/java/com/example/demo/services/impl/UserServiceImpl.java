package com.example.demo.services.impl;

import com.example.demo.dto.LoginUserDto;
import com.example.demo.dto.RegisterUserDto;
import com.example.demo.entities.User;
import com.example.demo.entities.UserType;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private ModelMapper modelMapper;
    private boolean isLogged;
    private String loggedUser;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public String registerUser(RegisterUserDto registerUserDto) {

        StringBuilder sb = new StringBuilder();

        if (!registerUserDto.getPassword().equals(registerUserDto.getConfirmPassword())) {
            return sb.append("Not equals password and confirm password").toString().trim();
        }

        User user = modelMapper.map(registerUserDto, User.class);

        if (this.userRepository.findByEmail(user.getEmail()).isPresent()) {
            return sb.append("Such user already exists").toString();
        }

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<User>> violations = validator.validate(user);


        if (userRepository.count() == 0) {
            user.setUserType(UserType.ADMINISTRATOR);
        } else {
            user.setUserType(UserType.USER);
        }

        if (violations.size() > 0) {
            for (ConstraintViolation<User> violation : violations) {
                sb.append(violation.getMessage()).append(System.lineSeparator());
            }
        } else {
            sb.append(String.format("%s was registered", user.getFullName()));
            this.userRepository.saveAndFlush(user);
        }


        return sb.toString().trim();
    }

    @Override
    public String loginUser(LoginUserDto loginUserDto) {

        StringBuilder sb = new StringBuilder();


        User user = this.userRepository.findByEmail(loginUserDto.getEmail()).orElse(null);

        if (user == null) {
            return sb.append(sb.append("Wrong email or password")).toString().trim();
        }

        if (isLogged) {
            return sb.append("User is already logged in").toString().trim();
        } else {
            isLogged = true;
            loggedUser = user.getFullName();
        }

        return sb.append("Successfully logged in ").append(loggedUser).toString().trim();
    }

    @Override
    public String logout() {

        StringBuilder sb = new StringBuilder();

        if (isLogged) {
            isLogged = false;
            sb.append(loggedUser).append(" logged out");
            loggedUser = "";
        }else{
            sb.append("Not logged in User");
        }

        return sb.toString().trim();
    }

    @Override
    public String getLoggedUser() {
        return this.loggedUser;
    }
}
