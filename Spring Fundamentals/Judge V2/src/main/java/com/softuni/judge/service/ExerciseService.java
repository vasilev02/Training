package com.softuni.judge.service;

import com.softuni.judge.model.entity.Exercise;
import com.softuni.judge.model.service.ExerciseServiceModel;

import java.util.List;

public interface ExerciseService {
    void addExercise(ExerciseServiceModel serviceModel);

    List<String> getAllExercises();

    Exercise getExerciseByName(String exercise);

    boolean checkExpireDate(String exercise);
}
