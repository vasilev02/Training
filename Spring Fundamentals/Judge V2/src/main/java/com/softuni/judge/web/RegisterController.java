package com.softuni.judge.web;

import com.softuni.judge.model.binding.UserAddBindingModel;
import com.softuni.judge.model.service.UserServiceModel;
import com.softuni.judge.security.CurrentUser;
import com.softuni.judge.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class RegisterController {

    private final UserService userService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    @Autowired
    public RegisterController(UserService userService, CurrentUser currentUser, ModelMapper modelMapper) {
        this.userService = userService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/register")
    public String showRegisterPage(Model model) {

        if (!currentUser.isAnonymous()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("userAddBindingModel")) {
            model.addAttribute("userAddBindingModel", new UserAddBindingModel());
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userAddBindingModel")
                                       UserAddBindingModel userAddBindingModel, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userAddBindingModel.getPassword().equals(userAddBindingModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userAddBindingModel", userAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userAddBindingModel", bindingResult);
            return "redirect:/users/register";

        }

        UserServiceModel userServiceModel = this.modelMapper.map(userAddBindingModel, UserServiceModel.class);
        userService.registerUser(userServiceModel);

        return "redirect:/users/login";
    }

}
