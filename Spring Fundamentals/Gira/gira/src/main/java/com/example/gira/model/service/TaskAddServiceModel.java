package com.example.gira.model.service;

import com.example.gira.model.entity.Classification;
import com.example.gira.model.enumeration.ClassificationEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class TaskAddServiceModel {

    private String name;
    private String description;
    private LocalDate dueDate;
    private ClassificationEnum classification;

    public TaskAddServiceModel() {
    }

    @Size(min = 3, max = 20, message = "Task name length must be between 3 and 20 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(min = 5, message = "Description length must be at least 5 characters")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "Choose date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Date and Time, that cannot be in the past")
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @NotNull(message = "Choose classification")
    public ClassificationEnum getClassification() {
        return classification;
    }

    public void setClassification(ClassificationEnum classification) {
        this.classification = classification;
    }

}
