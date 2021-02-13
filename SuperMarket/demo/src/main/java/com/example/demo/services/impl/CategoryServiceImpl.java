package com.example.demo.services.impl;

import com.example.demo.models.entities.Category;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.CategoryService;
import com.example.demo.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidationUtil validationUtil) {
        this.categoryRepository = categoryRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public String addCategory(String name) {

        StringBuilder sb = new StringBuilder();

        Category findCategory = this.categoryRepository.findByName(name);

        if (findCategory == null) {

            Category category = new Category();
            category.setName(name);

            if (validationUtil.isValid(category)) {
                this.categoryRepository.saveAndFlush(category);
                sb.append("Successfully added category!").append(System.lineSeparator());
                sb.append("============================").append(System.lineSeparator());
            } else {
                sb.append("Category name must be between 2 and 20 characters!").append(System.lineSeparator());
            }

        } else {

            sb.append(String.format("Category with name %s already exists", name)).append(System.lineSeparator());

        }
        return sb.toString().trim();
    }
}
