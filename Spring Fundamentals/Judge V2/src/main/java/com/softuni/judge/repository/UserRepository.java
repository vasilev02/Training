package com.softuni.judge.repository;

import com.softuni.judge.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User getUsersByUsername(String username);

    User getUserByUsername(String name);

    @Query(value = "SELECT u.username FROM User AS u ORDER BY u.username")
    List<String> getAllUsernames();

}
