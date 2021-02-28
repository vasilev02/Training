package com.softuni.judge.repository;

import com.softuni.judge.model.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, String> {

    Homework findTop1ByOrderByComments();

    Homework getHomeworkById(String id);

}
