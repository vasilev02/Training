package com.softuni.judge.service.impl;

import com.softuni.judge.model.entity.Role;
import com.softuni.judge.model.enumeration.RoleEnum;
import com.softuni.judge.model.service.RoleServiceModel;
import com.softuni.judge.repository.RoleRepository;
import com.softuni.judge.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {

        if (this.roleRepository.count() == 0) {

            Role admin = new Role();
            admin.setName(RoleEnum.ADMIN);
            this.roleRepository.saveAndFlush(admin);

            Role user = new Role();
            user.setName(RoleEnum.USER);
            this.roleRepository.saveAndFlush(user);
        }

    }


    @Override
    public RoleServiceModel findByName(String name) {

        Role findRole = this.roleRepository.findByName(RoleEnum.valueOf(name));

        if (findRole != null) {

            return this.modelMapper.map(findRole, RoleServiceModel.class);

        }

        return null;
    }

    @Override
    public Role findRoleByName(String name) {
        return this.roleRepository.findByName(RoleEnum.valueOf(name));
    }
}
