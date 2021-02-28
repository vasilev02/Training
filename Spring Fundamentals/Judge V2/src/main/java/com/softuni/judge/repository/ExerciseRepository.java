package com.softuni.judge.repository;

import com.softuni.judge.model.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,String> {

    Optional<Exercise> getExerciseByName(String name);

    @Query(value = "SELECT e.name FROM Exercise AS e ORDER BY e.name")
    List<String> getAllExercises();

}
