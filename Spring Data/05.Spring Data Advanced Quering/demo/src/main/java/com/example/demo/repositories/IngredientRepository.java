package com.example.demo.repositories;

import com.example.demo.entities.Ingredient;
import com.example.demo.entities.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Long> {

    @Query(value = "SELECT i FROM ingredients AS i WHERE i.name LIKE :input%")
    Set<Ingredient> findAllByNameEquals(String input);

    Set<Ingredient> findAllByNameIn(Set<String> set);

    Set<Ingredient> findCountByPriceLessThan(BigDecimal decimal);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM ingredients AS i WHERE i.name = :name")
    void deleteIngredientByName(String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ingredients AS i SET i.price = i.price * 1.1 WHERE i.name = :name")
    void updateIngredientPriceBy(String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ingredients AS i SET i.price = i.price * 1.1 WHERE i.name IN :set")
    void updateIngredientPriceByGivenSet(Set<String> set);


}
