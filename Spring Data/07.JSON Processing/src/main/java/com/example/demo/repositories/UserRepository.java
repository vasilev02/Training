package com.example.demo.repositories;

import com.example.demo.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);

    @Query(value = "SELECT u FROM User AS u WHERE u.seller.size > 0 ORDER BY u.lastName ASC, u.firstName ASC")
    Set<User> getUsers();

    @Query(value = "SELECT u FROM User AS u WHERE u.buyer.size > 0")
    Set<User> getUsersSales();


}
