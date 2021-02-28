package com.softuni.judge.service.impl;

import com.softuni.judge.model.binding.HomeworkAddBindingModel;
import com.softuni.judge.model.entity.Homework;
import com.softuni.judge.model.service.HomeworkAddCommentServiceModel;
import com.softuni.judge.model.service.HomeworkCheckServiceModel;
import com.softuni.judge.repository.HomeworkRepository;
import com.softuni.judge.security.CurrentUser;
import com.softuni.judge.service.ExerciseService;
import com.softuni.judge.service.HomeworkService;
import com.softuni.judge.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository homeworkRepository;
    private final ExerciseService exerciseService;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeworkServiceImpl(HomeworkRepository homeworkRepository, ExerciseService exerciseService, CurrentUser currentUser, UserService userService, ModelMapper modelMapper) {
        this.homeworkRepository = homeworkRepository;
        this.exerciseService = exerciseService;
        this.currentUser = currentUser;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addHomework(HomeworkAddBindingModel homeworkAddBindingModel) {

        Homework homework = new Homework();

        homework.setAddedOn(LocalDateTime.now());
        homework.setAuthor(this.userService.getUserByUsername(currentUser.getUsername()));
        homework.setExercise(this.exerciseService.getExerciseByName(homeworkAddBindingModel.getExercise()));
        homework.setGit(homeworkAddBindingModel.getGitAddress());

        this.homeworkRepository.saveAndFlush(homework);
    }

    @Override
    public HomeworkCheckServiceModel getHomeworkToCheck() {
        Homework homework = this.homeworkRepository.findTop1ByOrderByComments();
        if(homework == null){
            return null;
        }
        return this.modelMapper.map(homework, HomeworkCheckServiceModel.class);
    }

    @Override
    public Homework getHomeworkBiId(String id) {
        return this.homeworkRepository.getHomeworkById(id);
    }


}
