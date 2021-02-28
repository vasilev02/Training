package com.softuni.judge.model.service;

import com.softuni.judge.model.entity.BaseEntity;
import com.softuni.judge.model.enumeration.RoleEnum;

public class RoleServiceModel extends BaseEntity {

    private RoleEnum name;

    public RoleServiceModel() {
    }

    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }
}
