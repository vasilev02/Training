package com.example.demo.service.impl;

import com.example.demo.model.entity.User;
import com.example.demo.model.service.UserRegisterServiceModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
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
    public boolean authenticateUser(String username, String password) {
        Optional<User> user = this.userRepository.findUserByUsername(username);

        if(user.isPresent()){
            return this.passwordEncoder.matches(password, user.get().getPassword());
        }
        return false;
    }

    @Override
    public User findUserByUsername(String username) {
        return this.userRepository.getUserByUsername(username);
    }
}
