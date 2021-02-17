package com.example.heroes.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserLogoutController {

    @GetMapping("/logout")
    public String logoutUser(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }

}
