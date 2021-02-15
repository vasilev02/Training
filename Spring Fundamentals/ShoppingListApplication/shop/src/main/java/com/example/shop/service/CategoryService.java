package com.example.shop.service;

import com.example.shop.model.entity.Category;
import com.example.shop.model.enumeration.CategoryEnum;

public interface CategoryService {
    void initializeCategories();

    Category getCategoryByName(CategoryEnum category);
}
