package com.example.shop.repository;

import com.example.shop.model.entity.Product;
import com.example.shop.model.enumeration.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query(value = "SELECT SUM(p.price) FROM Product AS p")
    BigDecimal getTotalPrice();

    List<Product> findProductByCategory_Name(CategoryEnum categoryEnum);

    Optional<Product> findProductByName(String name);
}
