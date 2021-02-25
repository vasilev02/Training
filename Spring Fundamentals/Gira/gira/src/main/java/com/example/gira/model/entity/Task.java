package com.example.gira.model.entity;

import com.example.gira.model.enumeration.ProgressEnum;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity{

    private String name;
    private String description;
    private ProgressEnum progress;
    private LocalDate dueDate;
    private Classification classification;
    private User user;

    public Task() {
    }

    @Column(name = "name", nullable = false , unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", nullable = false , columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "progress", nullable = false )
    public ProgressEnum getProgress() {
        return progress;
    }

    public void setProgress(ProgressEnum progress) {
        this.progress = progress;
    }

    @Column(name = "due_date", nullable = false)
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @ManyToOne
    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
