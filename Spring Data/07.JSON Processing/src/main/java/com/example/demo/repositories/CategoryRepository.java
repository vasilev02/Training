package com.example.demo.repositories;

import com.example.demo.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findById(int id);

    @Query(value = "SELECT c FROM Category AS c JOIN Product AS p WHERE c.name IN p.categories ORDER BY c.name", nativeQuery = true)
    Set<Category> findEachCategoryInformation();

    @Query(value = "SELECT c FROM Category AS c")
    Set<Category> getAllCategories();

}
