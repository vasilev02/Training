package com.example.demo.repositories;

import com.example.demo.entities.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label,Long> {

}
