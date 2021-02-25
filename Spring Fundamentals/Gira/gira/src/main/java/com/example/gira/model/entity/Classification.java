package com.example.gira.model.entity;

import com.example.gira.model.enumeration.ClassificationEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "classifications")
public class Classification extends BaseEntity {

    private ClassificationEnum classificationName;
    private String description;

    public Classification() {
    }

    public Classification(ClassificationEnum classificationName, String description) {
        this.classificationName = classificationName;
        this.description = description;
    }

    @Column(name = "classification_name", nullable = false , unique = true)
    public ClassificationEnum getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(ClassificationEnum classificationName) {
        this.classificationName = classificationName;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
