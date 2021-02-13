package com.example.demo.models.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {

    private String name;
    private Set<Shop> shops;

    public Town() {
    }

    @Column(name = "name", nullable = false, unique = true)
    @Size(min = 2, max = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "town", targetEntity = Shop.class)
    public Set<Shop> getShops() {
        return shops;
    }

    public void setShops(Set<Shop> shops) {
        this.shops = shops;
    }
}
