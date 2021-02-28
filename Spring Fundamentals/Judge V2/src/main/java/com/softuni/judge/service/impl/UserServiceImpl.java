package com.softuni.judge.service.impl;

import com.softuni.judge.model.entity.User;
import com.softuni.judge.model.enumeration.RoleEnum;
import com.softuni.judge.model.service.RoleServiceModel;
import com.softuni.judge.model.service.UserProfileServiceModel;
import com.softuni.judge.model.service.UserServiceModel;
import com.softuni.judge.repository.UserRepository;
import com.softuni.judge.security.CurrentUser;
import com.softuni.judge.service.RoleService;
import com.softuni.judge.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final CurrentUser currentUser;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService, CurrentUser currentUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {

        RoleServiceModel roleServiceModel = null;

        if (this.userRepository.count() == 0) {
            roleServiceModel = this.roleService.findByName("ADMIN");
        } else {
            roleServiceModel = this.roleService.findByName("USER");
        }

        userServiceModel.setRole(roleServiceModel);

        User user = this.modelMapper.map(userServiceModel, User.class);

        user.setPassword(this.passwordEncoder.encode(userServiceModel.getPassword()));

        this.userRepository.saveAndFlush(user);

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel getUserByUsernameAndPassword(String username, String password) {

        User user = this.userRepository.getUsersByUsername(username);

        if (user == null || !this.passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }

        return this.modelMapper.map(user, UserServiceModel.class);

    }

    @Override
    public void login(UserServiceModel userServiceModel) {

        this.currentUser.setUsername(userServiceModel.getUsername());
        this.currentUser.setRole(userServiceModel.getRole().getName());

    }

    @Override
    public void logout() {
        this.currentUser.setUsername(null);
        this.currentUser.setRole(null);
    }

    @Override
    public List<String> getUsernames() {
        return this.userRepository.getAllUsernames();
    }

    @Override
    public void changeRole(String username, String role) {
        User user = this.userRepository.getUsersByUsername(username);
        if (user != null) {
            if (!user.getRole().getName().equals(RoleEnum.valueOf(role))) {
                user.setRole(this.roleService.findRoleByName(role));
                this.userRepository.save(user);
            }
        }
    }

    @Override
    public User getUserByUsername(String name) {
        return this.userRepository.getUserByUsername(name);
    }

    @Override
    public UserProfileServiceModel getUserInfo() {
        User user = this.userRepository.getUserByUsername(this.currentUser.getUsername());

        UserProfileServiceModel serviceModel = this.modelMapper.map(user, UserProfileServiceModel.class);

        Set<String> homeworks = user.getHomeworks().stream().map(h -> h.getExercise().getName()).collect(Collectors.toSet());

        serviceModel.setHomeworks(homeworks);

        return serviceModel;
    }

}
