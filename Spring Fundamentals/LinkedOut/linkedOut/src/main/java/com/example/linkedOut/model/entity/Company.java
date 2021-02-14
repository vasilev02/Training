package com.example.linkedOut.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity {

    private String name;
    private String town;
    private BigDecimal budget;
    private String description;
    private List<Employee> employees;

    public Company() {
    }

    @Column(name = "name", unique = true, nullable = false)
    @Size(min = 2, max = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "town", nullable = false)
    @Size(min = 2, max = 10)
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Column(name = "budget", nullable = false)
    @Positive
    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    @Size(min = 10)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "company", targetEntity = Employee.class,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
