package com.example.andreys.init;

import com.example.andreys.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoryInitializer implements CommandLineRunner {

    private final CategoryService categoryService;

    @Autowired
    public CategoryInitializer(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {

        categoryService.initCategories();

    }
}
