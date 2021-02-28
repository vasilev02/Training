package com.softuni.judge.service;

import com.softuni.judge.model.binding.HomeworkAddBindingModel;
import com.softuni.judge.model.entity.Homework;
import com.softuni.judge.model.service.HomeworkCheckServiceModel;

public interface HomeworkService {
    void addHomework(HomeworkAddBindingModel homeworkAddBindingModel);

    HomeworkCheckServiceModel getHomeworkToCheck();

    Homework getHomeworkBiId(String id);
}
