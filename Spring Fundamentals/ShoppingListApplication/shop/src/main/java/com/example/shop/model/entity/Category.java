package com.example.shop.model.entity;

import com.example.shop.model.enumeration.CategoryEnum;
import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    private CategoryEnum name;
    private String description;

    public Category() {
    }

    public Category(CategoryEnum name) {
        this.name = name;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    public CategoryEnum getName() {
        return name;
    }

    public void setName(CategoryEnum name) {
        this.name = name;
    }


    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
