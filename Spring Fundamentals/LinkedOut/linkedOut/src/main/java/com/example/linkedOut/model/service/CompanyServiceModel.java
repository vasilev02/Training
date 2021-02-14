package com.example.linkedOut.model.service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class CompanyServiceModel {

    private String name;
    private String town;
    private BigDecimal budget;
    private String description;

    public CompanyServiceModel() {
    }

    @NotNull(message = "Company is missing")
    @Size(min = 2, max = 10, message = "Company name must be between 2 and 10 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Town is missing")
    @Size(min = 2, max = 10, message = "Town name must be between 2 and 10 characters")
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @NotNull(message = "Budget is missing")
    @Positive(message = "Budget must be positive number")
    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    @NotNull(message = "Description is missing")
    @Size(min = 10, message = "Description must be at least 10 characters")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
