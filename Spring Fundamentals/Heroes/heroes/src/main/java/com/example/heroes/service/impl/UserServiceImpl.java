package com.example.heroes.service.impl;

import com.example.heroes.model.entity.User;
import com.example.heroes.model.service.UserLoginServiceModel;
import com.example.heroes.model.service.UserRegisterServiceModel;
import com.example.heroes.repository.UserRepository;
import com.example.heroes.service.UserService;
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
    public boolean checkUsernameExistence(String username) {
        return this.userRepository.findUserByUsername(username).isPresent();
    }

    @Override
    public boolean checkEmailExistence(String email) {
        return this.userRepository.findUserByEmail(email).isPresent();
    }

    @Override
    public boolean checkUsernameExistenceLogin(String username) {
        return this.userRepository.findUserByUsername(username).isEmpty();
    }

    @Override
    public boolean checkPassword(UserLoginServiceModel serviceModel) {
        Optional<User> user = this.userRepository.findUserByUsername(serviceModel.getUsername());

        if(!this.passwordEncoder.matches(serviceModel.getPassword(), user.get().getPassword())){
            return true;
        }
        return false;
    }
}
