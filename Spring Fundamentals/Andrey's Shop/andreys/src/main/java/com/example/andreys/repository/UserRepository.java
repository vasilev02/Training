package com.example.andreys.repository;

import com.example.andreys.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

}
