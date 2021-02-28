package com.softuni.judge.security;

import com.softuni.judge.model.enumeration.RoleEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private String username;
    private RoleEnum role;

    public CurrentUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public boolean isAnonymous() {
        return this.username == null;
    }

    public boolean isAdmin(){
        return this.role == RoleEnum.ADMIN;
    }

}
