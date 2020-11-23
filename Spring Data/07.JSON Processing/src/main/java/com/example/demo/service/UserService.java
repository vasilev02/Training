package com.example.demo.service;

import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.dto.UserSeedDto;
import com.example.demo.domain.dto.UsersInfoDto;
import com.example.demo.domain.entities.User;

import java.util.Set;

public interface UserService {

    void seedUsersInDB(UserSeedDto[] userSeedDtos);

    Set<UserDto> getUsersInfo();


    UsersInfoDto getUsersWithAtLeastOneBuyer();

}
