package com.softuni.judge.service.impl;

import com.softuni.judge.model.entity.Exercise;
import com.softuni.judge.model.service.ExerciseServiceModel;
import com.softuni.judge.repository.ExerciseRepository;
import com.softuni.judge.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addExercise(ExerciseServiceModel serviceModel) {

        Optional<Exercise> findExercise = this.exerciseRepository.getExerciseByName(serviceModel.getName());

        if (findExercise.isEmpty()) {
            Exercise exercise = this.modelMapper.map(serviceModel, Exercise.class);

            this.exerciseRepository.saveAndFlush(exercise);
        }

    }

    @Override
    public List<String> getAllExercises() {
        return this.exerciseRepository.getAllExercises();
    }

    @Override
    public Exercise getExerciseByName(String exercise) {
        return this.exerciseRepository.getExerciseByName(exercise).get();
    }

    @Override
    public boolean checkExpireDate(String exercise) {

        Optional<Exercise> findExercise = this.exerciseRepository.getExerciseByName(exercise);

        if (findExercise.get().getDueDate().isAfter(LocalDateTime.now())) {
            return true;
        }
        return false;
    }
}
