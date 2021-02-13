package com.example.demo.repositories;

import com.example.demo.models.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {

    Seller findByFirstNameAndLastName(String firstName, String lastName);

    @Query(value = "SELECT s FROM Seller AS s WHERE s.shop.name = :shopName")
    Set<Seller> getAllSellersByGivenShopName(String shopName);

}
