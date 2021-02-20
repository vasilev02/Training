package com.example.andreys.model.binding;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class UserRegisterBindingModel {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private BigDecimal budget;

    public UserRegisterBindingModel() {
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

    @NotBlank(message = "Type your confirm password")
    @Size(min = 2, message = "Confirm password must be at least 2 characters")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotBlank(message = "Type your email")
    @Email(message = "Email is not the right format")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message = "Type your budget")
    @Positive(message = "Budget is not positive")
    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
