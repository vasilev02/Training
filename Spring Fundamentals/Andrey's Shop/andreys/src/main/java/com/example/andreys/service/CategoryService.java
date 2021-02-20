package com.example.andreys.service;

import com.example.andreys.model.entity.Category;
import com.example.andreys.model.enumeration.CategoryEnum;

public interface CategoryService {
    void initCategories();

    Category getCategoryByName(CategoryEnum category);
}
