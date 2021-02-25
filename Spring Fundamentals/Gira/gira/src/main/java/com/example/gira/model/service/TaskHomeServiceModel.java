package com.example.gira.model.service;

import com.example.gira.model.entity.Classification;
import com.example.gira.model.enumeration.ProgressEnum;

import java.time.LocalDate;

public class TaskHomeServiceModel {

    private String id;
    private String name;
    private ProgressEnum progress;
    private LocalDate dueDate;
    private String classification;
    private String username;

    public TaskHomeServiceModel() {
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

    public ProgressEnum getProgress() {
        return progress;
    }

    public void setProgress(ProgressEnum progress) {
        this.progress = progress;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
