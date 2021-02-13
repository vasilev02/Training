package com.example.demo.services.impl;

import com.example.demo.models.entities.Town;
import com.example.demo.repositories.TownRepository;
import com.example.demo.services.TownService;
import com.example.demo.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public String addTown(String name) {

        StringBuilder sb = new StringBuilder();

        Town findTown = this.townRepository.findByName(name);

        if (findTown == null) {

            Town town = new Town();
            town.setName(name);

            if (validationUtil.isValid(town)) {
                this.townRepository.saveAndFlush(town);
                sb.append("Successfully added town!").append(System.lineSeparator());
                sb.append("============================").append(System.lineSeparator());
            } else {
                sb.append("Town name must be between 2 and 20 characters!").append(System.lineSeparator());
            }

        } else {
            sb.append(String.format("Town with name %s already exists", name)).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
