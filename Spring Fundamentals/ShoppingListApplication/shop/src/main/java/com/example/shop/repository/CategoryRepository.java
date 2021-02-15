package com.example.shop.repository;

import com.example.shop.model.entity.Category;
import com.example.shop.model.enumeration.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Category findCategoryByName(CategoryEnum categoryEnum);

}
