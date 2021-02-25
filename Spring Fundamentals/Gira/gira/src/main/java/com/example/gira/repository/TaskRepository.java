package com.example.gira.repository;

import com.example.gira.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

    Optional<Task> findTaskByName(String name);

    Task findTaskById(String id);

    @Query(value = "SELECT t FROM Task AS t ORDER BY t.dueDate DESC")
    List<Task> getAllTasks();

}
