package com.example.andreys.web;

import com.example.andreys.model.binding.UserRegisterBindingModel;
import com.example.andreys.model.service.UserRegisterServiceModel;
import com.example.andreys.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserRegisterController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRegisterController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String showRegisterPage(HttpSession httpSession, Model model) {

        if(httpSession.getAttribute("user") != null){
            return "redirect:/";
        }

        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
            model.addAttribute("existingUsername", false);
            model.addAttribute("existingEmail", false);
            model.addAttribute("notEqualsPasswords", false);
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterBindingModel userRegisterBindingModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:/users/register";
        }

        if (this.userService.containsUsername(userRegisterBindingModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("existingUsername", true);
            return "redirect:/users/register";
        }

        if (this.userService.containsEmail(userRegisterBindingModel.getEmail())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("existingEmail", true);
            return "redirect:/users/register";
        }

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("notEqualsPasswords", true);
            return "redirect:/users/register";
        }


        UserRegisterServiceModel serviceModel = this.modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class);

        this.userService.registerUser(serviceModel);

        return "redirect:/users/login";
    }

}
