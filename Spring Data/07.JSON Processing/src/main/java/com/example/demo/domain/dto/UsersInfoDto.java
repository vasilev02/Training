package com.example.demo.domain.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Set;

public class UsersInfoDto implements Serializable {

    @Expose
    private int usersCount;

    @Expose
    private Set<UserSoldProductsDto> users;


    public UsersInfoDto() {
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public Set<UserSoldProductsDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserSoldProductsDto> users) {
        this.users = users;
    }

}
