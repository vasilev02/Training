package com.example.andreys.web;

import com.example.andreys.model.binding.UserLoginBindingModel;
import com.example.andreys.model.service.UserLoginServiceModel;
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
public class UserLoginController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserLoginController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String showLoginPage(HttpSession httpSession, Model model) {

        if(httpSession.getAttribute("user") != null){
            return "redirect:/";
        }

        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
            model.addAttribute("notAuthenticated", false);
        }

        return "login";
    }

    @PostMapping("/login")
    public String log(@Valid UserLoginBindingModel userLoginBindingModel,
                      BindingResult bindingResult, RedirectAttributes redirectAttributes,
                      HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:/users/login";
        }


        if (!this.userService.authenticateUser(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword())) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("notAuthenticated", true);
            return "redirect:/users/login";
        }

        UserLoginServiceModel serviceModel = this.modelMapper.map(userLoginBindingModel, UserLoginServiceModel.class);

        httpSession.setAttribute("user", serviceModel);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }

}
