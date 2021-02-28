package com.softuni.judge.service;

import com.softuni.judge.model.entity.Role;
import com.softuni.judge.model.service.RoleServiceModel;

public interface RoleService {

    RoleServiceModel findByName(String name);

    Role findRoleByName(String name);

}
