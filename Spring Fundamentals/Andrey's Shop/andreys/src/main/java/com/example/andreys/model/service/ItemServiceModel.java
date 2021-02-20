package com.example.andreys.model.service;

import com.example.andreys.model.entity.Category;
import com.example.andreys.model.enumeration.CategoryEnum;

import java.math.BigDecimal;

public class ItemServiceModel {

    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String gender;
    private CategoryEnum category;

    public ItemServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }
}
