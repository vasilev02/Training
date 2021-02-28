package com.softuni.judge.repository;

import com.softuni.judge.model.entity.Role;
import com.softuni.judge.model.enumeration.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByName(RoleEnum name);

}
