package com.example.andreys.service.impl;

import com.example.andreys.model.entity.Category;
import com.example.andreys.model.enumeration.CategoryEnum;
import com.example.andreys.repository.CategoryRepository;
import com.example.andreys.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {

        if (this.categoryRepository.count() == 0) {
            CategoryEnum[] values = CategoryEnum.values();

            for (CategoryEnum current : values) {
                String description = "Description for " + current;
                this.categoryRepository.saveAndFlush(new Category(current, description));
            }
        }
    }

    @Override
    public Category getCategoryByName(CategoryEnum category) {
        return this.categoryRepository.findCategoryByCategory(category);
    }
}
