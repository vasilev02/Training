package com.example.demo.repositories;

import com.example.demo.entities.Label;
import com.example.demo.entities.Shampoo;
import com.example.demo.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo,Long> {

    Set<Shampoo> findAllByBrand(String brand);

    Set<Shampoo> findAllBySizeOrLabel_IdOrderByPrice(Size size, long labelId);

    Set<Shampoo> findAllBySizeEqualsOrderById(Size sizeValue);

    Set<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    @Query(value = "SELECT s FROM shampoos AS s JOIN s.ingredients AS i where i.name IN :givenIngredients")
    Set<Shampoo> selectShampoosByIngredients(Set<String> givenIngredients);

    @Query(value = "SELECT s FROM shampoos AS s WHERE s.ingredients.size < :count")
    Set<Shampoo> selectAllShampoosWithCountUnder(int count);

}
