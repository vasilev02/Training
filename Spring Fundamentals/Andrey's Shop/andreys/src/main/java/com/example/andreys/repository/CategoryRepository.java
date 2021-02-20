package com.example.andreys.repository;

import com.example.andreys.model.entity.Category;
import com.example.andreys.model.enumeration.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Category findCategoryByCategory(CategoryEnum category);

}
