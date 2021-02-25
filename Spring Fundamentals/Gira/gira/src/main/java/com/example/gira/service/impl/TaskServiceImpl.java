package com.example.gira.service.impl;

import com.example.gira.model.entity.Classification;
import com.example.gira.model.entity.Task;
import com.example.gira.model.entity.User;
import com.example.gira.model.enumeration.ProgressEnum;
import com.example.gira.model.service.TaskAddServiceModel;
import com.example.gira.model.service.TaskHomeServiceModel;
import com.example.gira.model.service.UserServiceModel;
import com.example.gira.repository.TaskRepository;
import com.example.gira.service.ClassificationService;
import com.example.gira.service.TaskService;
import com.example.gira.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final ClassificationService classificationService;
    private final ModelMapper modelMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserService userService, ClassificationService classificationService, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.classificationService = classificationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public TaskAddServiceModel addTask(TaskAddServiceModel serviceModel, UserServiceModel userServiceModel) {

        Task task = this.modelMapper.map(serviceModel, Task.class);

        User user = this.userService.getUserByEmail(userServiceModel.getEmail());

        Classification classification = this.classificationService.getClassificationByName(serviceModel.getClassification());

        task.setProgress(ProgressEnum.OPEN);
        task.setUser(user);
        task.setClassification(classification);

        this.taskRepository.saveAndFlush(task);
        return serviceModel;
    }

    @Override
    public boolean checkTaskNameIfExist(String name) {
        return this.taskRepository.findTaskByName(name).isPresent();
    }

    @Override
    public List<TaskHomeServiceModel> getAllTasks() {
        return this.taskRepository.getAllTasks().stream().map(task -> {
            TaskHomeServiceModel serviceModel = this.modelMapper.map(task, TaskHomeServiceModel.class);
            User user = task.getUser();
            Classification classification = task.getClassification();

            serviceModel.setClassification(classification.getClassificationName().toString());
            serviceModel.setUsername(user.getUsername());
            return serviceModel;
        }).collect(Collectors.toList());
    }

    @Override
    public void progress(String id) {

        Task task = this.taskRepository.findTaskById(id);

        if (task.getProgress().equals(ProgressEnum.COMPLETED)) {
            task.setUser(null);
            task.setClassification(null);
            this.taskRepository.delete(task);
            return;
        }

        if (task.getProgress().equals(ProgressEnum.OPEN)) {
            task.setProgress(ProgressEnum.IN_PROGRESS);
            this.taskRepository.saveAndFlush(task);
            return;
        }

        if (task.getProgress().equals(ProgressEnum.IN_PROGRESS)) {
            task.setProgress(ProgressEnum.COMPLETED);
            this.taskRepository.saveAndFlush(task);
        }

    }
}
