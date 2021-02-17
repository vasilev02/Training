package com.example.heroes.web;

import com.example.heroes.model.binding.UserLoginBindingModel;
import com.example.heroes.model.service.UserLoginServiceModel;
import com.example.heroes.service.UserService;
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
public class UserLoginController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserLoginController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
            model.addAttribute("usernameNotExists", false);
            model.addAttribute("passwordsNotEquals", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String logUser(@Valid UserLoginBindingModel userLoginBindingModel,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes,
                          HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:/users/login";
        }

        if (this.userService.checkUsernameExistenceLogin(userLoginBindingModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("usernameNotExists", true);
            return "redirect:/users/login";
        }

        UserLoginServiceModel serviceModel = this.modelMapper.map(userLoginBindingModel, UserLoginServiceModel.class);

        if (this.userService.checkPassword(serviceModel)) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("passwordsNotEquals", true);
            return "redirect:/users/login";
        }

        httpSession.setAttribute("user", serviceModel);
        return "redirect:/";
    }

}
