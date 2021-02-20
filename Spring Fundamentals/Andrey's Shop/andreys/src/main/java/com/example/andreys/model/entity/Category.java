package com.example.andreys.model.entity;

import com.example.andreys.model.enumeration.CategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    private CategoryEnum category;
    private String description;

    public Category() {
    }

    public Category(CategoryEnum category, String description) {
        this.category = category;
        this.description = description;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category", nullable = false)
    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
