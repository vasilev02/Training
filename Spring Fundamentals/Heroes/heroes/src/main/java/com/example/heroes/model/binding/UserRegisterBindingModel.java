package com.example.heroes.model.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String country;

    public UserRegisterBindingModel() {
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

    @NotBlank(message = "Type your confirm password")
    @Size(min = 3, max = 20, message = "Confirm password must be between 3 and 20 characters inclusive")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotBlank(message = "Type your email")
    @Email(message = "Enter valid email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank(message = "Type your country")
    @Size(min = 3, max = 20, message = "Country must be between 3 and 20 characters inclusive")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
