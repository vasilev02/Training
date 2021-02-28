package com.softuni.judge.web;

import com.softuni.judge.security.CurrentUser;
import com.softuni.judge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class LogoutController {

    private final UserService userService;
    private final CurrentUser currentUser;

    @Autowired
    public LogoutController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/logout")
    public String logout() {

        if (currentUser.isAnonymous()) {
            return "redirect:/";
        }

        this.userService.logout();
        return "redirect:/";
    }

}
