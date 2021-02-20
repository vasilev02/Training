package com.example.andreys.service.impl;

import com.example.andreys.model.entity.User;
import com.example.andreys.model.service.UserRegisterServiceModel;
import com.example.andreys.repository.UserRepository;
import com.example.andreys.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserRegisterServiceModel registerUser(UserRegisterServiceModel serviceModel) {

        User user = this.modelMapper.map(serviceModel, User.class);
        user.setPassword(this.passwordEncoder.encode(serviceModel.getPassword()));
        this.userRepository.saveAndFlush(user);
        return serviceModel;
    }

    @Override
    public boolean containsUsername(String username) {
        return this.userRepository.findUserByUsername(username).isPresent();
    }

    @Override
    public boolean containsEmail(String email) {
        return this.userRepository.findUserByEmail(email).isPresent();
    }


    @Override
    public boolean authenticateUser(String username, String password) {

        Optional<User> user = this.userRepository.findUserByUsername(username);

        if(user.isPresent()){
            return this.passwordEncoder.matches(password, user.get().getPassword());
        }
        return false;
    }
}
