package com.softuni.judge.model.service;

import java.util.Set;

public class UserProfileServiceModel {

    private String username;
    private String email;
    private String git;
    private Set<String> homeworks;

    public UserProfileServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }

    public Set<String> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(Set<String> homeworks) {
        this.homeworks = homeworks;
    }
}
