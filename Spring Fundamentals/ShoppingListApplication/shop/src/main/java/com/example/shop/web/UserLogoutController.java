package com.example.shop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserLogoutController {

    @GetMapping("/logout")
    public String logoutUser(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }

}
