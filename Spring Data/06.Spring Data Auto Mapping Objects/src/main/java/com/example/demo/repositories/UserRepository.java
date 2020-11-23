package com.example.demo.repositories;

import com.example.demo.entities.Game;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findAllByFullName(String name);

    @Query(value = "SELECT u.games FROM User AS u WHERE u.fullName = :name")
    Set<Game> getAllGamesOfUser(String name);

}
