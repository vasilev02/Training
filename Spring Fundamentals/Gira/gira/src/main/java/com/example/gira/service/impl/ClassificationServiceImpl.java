package com.example.gira.service.impl;

import com.example.gira.model.entity.Classification;
import com.example.gira.model.enumeration.ClassificationEnum;
import com.example.gira.repository.ClassificationRepository;
import com.example.gira.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassificationServiceImpl implements ClassificationService {

    private final ClassificationRepository classificationRepository;

    @Autowired
    public ClassificationServiceImpl(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Override
    public Classification getClassificationByName(ClassificationEnum classification) {
        return this.classificationRepository.getClassificationByClassificationName(classification);
    }

    @Override
    public void initializeClassifications() {
        if (this.classificationRepository.count() == 0) {
            ClassificationEnum[] values = ClassificationEnum.values();
            for (ClassificationEnum current : values) {
                String description = "Description for " + current;
                this.classificationRepository.saveAndFlush(new Classification(current, description));
            }
        }
    }
}
