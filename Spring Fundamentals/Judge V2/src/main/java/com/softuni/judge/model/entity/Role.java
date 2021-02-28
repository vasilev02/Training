package com.softuni.judge.model.entity;

import com.softuni.judge.model.enumeration.RoleEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    private RoleEnum name;
    private Set<User> user;

    public Role() {
    }

    @Column(name = "name", nullable = false)
    @Enumerated(value = EnumType.STRING)
    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "role", targetEntity = User.class)
    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }
}
