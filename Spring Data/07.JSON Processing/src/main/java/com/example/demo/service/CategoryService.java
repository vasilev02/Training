package com.example.demo.service;

import com.example.demo.domain.dto.CategoryDto;
import com.example.demo.domain.dto.CategorySeedDto;

import java.util.Set;

public interface CategoryService {

    void seedCategoriesInDB(CategorySeedDto[] categorySeedDtos);

    Set<CategoryDto> getCategoriesInfo();

}
