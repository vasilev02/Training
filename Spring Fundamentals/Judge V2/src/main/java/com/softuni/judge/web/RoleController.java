package com.softuni.judge.web;

import com.softuni.judge.security.CurrentUser;
import com.softuni.judge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/roles")
public class RoleController {

    private final UserService userService;
    private final CurrentUser currentUser;

    @Autowired
    public RoleController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/add")
    public String changeRolePage(Model model) {

        if (currentUser.isAnonymous()) {
            return "redirect:/";
        }

        model.addAttribute("names", this.userService.getUsernames());
        return "role-add";
    }

    @PostMapping("/add")
    public String changeUserRole(@RequestParam String username, @RequestParam String role) {

        this.userService.changeRole(username, role.toUpperCase());

        return "redirect:/";
    }

}
