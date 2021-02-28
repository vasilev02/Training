package com.softuni.judge.repository;

import com.softuni.judge.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,String> {

    @Query(value = "SELECT COUNT(c) FROM Comment AS c WHERE c.score = :grade")
    Integer getGradesFor(int grade);

}
