package com.softuni.judge.web;

import com.softuni.judge.model.service.UserProfileServiceModel;
import com.softuni.judge.model.view.UserProfileViewModel;
import com.softuni.judge.security.CurrentUser;
import com.softuni.judge.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class ProfileController {

    private final UserService userService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    @Autowired
    public ProfileController(UserService userService, CurrentUser currentUser, ModelMapper modelMapper) {
        this.userService = userService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/profile")
    public String showProfilePage(Model model) {

        if (currentUser.isAnonymous()) {
            return "redirect:/";
        }

        UserProfileServiceModel serviceModel = this.userService.getUserInfo();
        model.addAttribute("user", this.modelMapper.map(serviceModel, UserProfileViewModel.class));
        return "profile";
    }

}
