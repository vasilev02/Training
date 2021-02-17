package com.example.heroes.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginBindingModel {

    private String username;
    private String password;

    public UserLoginBindingModel() {
    }

    @NotBlank(message = "Type your username")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters inclusive")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank(message = "Type your password")
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters inclusive")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
