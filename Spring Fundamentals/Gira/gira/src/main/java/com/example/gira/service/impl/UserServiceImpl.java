package com.example.gira.service.impl;

import com.example.gira.model.service.UserRegisterServiceModel;
import com.example.gira.model.entity.User;
import com.example.gira.repository.UserRepository;
import com.example.gira.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserRegisterServiceModel registerUser(UserRegisterServiceModel serviceModel) {

        User user = this.modelMapper.map(serviceModel, User.class);
        user.setPassword(this.passwordEncoder.encode(serviceModel.getPassword()));
        this.userRepository.saveAndFlush(user);
        return serviceModel;
    }

    @Override
    public boolean checkUsernameIfExists(String username) {
        return this.userRepository.findUserByUsername(username).isPresent();
    }

    @Override
    public boolean checkEmailIfExists(String email) {
        return this.userRepository.findUserByEmail(email).isPresent();
    }

    @Override
    public boolean authenticateUser(String email, String password) {

        Optional<User> user = this.userRepository.findUserByEmail(email);

        if(user.isEmpty()){
            return false;
        }
        return this.passwordEncoder.matches(password, user.get().getPassword());
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userRepository.findUserByEmail(email).get();
    }
}
