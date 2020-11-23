package com.example.demo.repositories;

import com.example.demo.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GameRepository extends JpaRepository<Game,Integer> {

    Optional<Game> findById(int id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Game WHERE id = :id")
    void deleteById(int id);

    @Query(value = "SELECT g FROM Game AS g")
    Set<Game> geAllGames();

    Optional<Game> findByTitle(String title);

}
