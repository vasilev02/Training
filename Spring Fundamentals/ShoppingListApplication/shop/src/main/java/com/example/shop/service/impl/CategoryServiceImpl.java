package com.example.shop.service.impl;

import com.example.shop.model.entity.Category;
import com.example.shop.model.enumeration.CategoryEnum;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.service.CategoryService;
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
    public void initializeCategories() {
        if (this.categoryRepository.count() == 0) {
            CategoryEnum[] values = CategoryEnum.values();
            for (CategoryEnum value : values) {
                this.categoryRepository.saveAndFlush(new Category(value));
            }
        }
    }

    @Override
    public Category getCategoryByName(CategoryEnum category) {
        return this.categoryRepository.findCategoryByName(category);
    }
}
