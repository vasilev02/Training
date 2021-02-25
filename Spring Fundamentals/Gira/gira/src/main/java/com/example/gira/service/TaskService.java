package com.example.gira.service;

import com.example.gira.model.service.TaskAddServiceModel;
import com.example.gira.model.service.TaskHomeServiceModel;
import com.example.gira.model.service.UserServiceModel;

import java.util.List;

public interface TaskService {
    TaskAddServiceModel addTask(TaskAddServiceModel serviceModel, UserServiceModel userServiceModel);

    boolean checkTaskNameIfExist(String name);

    List<TaskHomeServiceModel> getAllTasks();

    void progress(String id);
}
