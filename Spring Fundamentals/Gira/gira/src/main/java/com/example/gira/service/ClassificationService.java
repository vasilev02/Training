package com.example.gira.service;

import com.example.gira.model.entity.Classification;
import com.example.gira.model.enumeration.ClassificationEnum;

public interface ClassificationService {
    Classification getClassificationByName(ClassificationEnum classification);

    void initializeClassifications();
}
