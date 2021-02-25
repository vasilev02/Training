package com.example.gira.init;

import com.example.gira.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ClassificationInitializer implements CommandLineRunner {

    private final ClassificationService classificationService;

    @Autowired
    public ClassificationInitializer(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.classificationService.initializeClassifications();
    }
}
