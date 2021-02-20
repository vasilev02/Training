package com.example.andreys.model.binding;

import com.example.andreys.model.enumeration.CategoryEnum;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ItemAddBindingModel {

    private String name;
    private String description;
    private BigDecimal price;
    private String gender;
    private CategoryEnum category;

    public ItemAddBindingModel() {
    }

    @NotBlank(message = "Type item name")
    @Size(min = 2, message = "Item name must be at least 2 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank(message = "Type item description")
    @Size(min = 3, message = "Description must be at least 3 characters")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Positive(message = "Price must be positive")
    @NotNull(message = "Type item price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotEmpty(message = "Choose gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @NotNull(message = "Choose category")
    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }
}
