package com.example.andreys.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginBindingModel {

    private String username;
    private String password;

    public UserLoginBindingModel() {
    }

    @NotBlank(message = "Type your username")
    @Size(min = 2, message = "Username must be at least 2 characters")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank(message = "Type your password")
    @Size(min = 2, message = "Password must be at least 2 characters")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
