package com.example.gira.web;

import com.example.gira.model.binding.TaskAddBindingModel;
import com.example.gira.model.service.TaskAddServiceModel;
import com.example.gira.model.service.UserServiceModel;
import com.example.gira.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ModelMapper modelMapper;

    @Autowired
    public TaskController(TaskService taskService, ModelMapper modelMapper) {
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String showAddTaskPage(Model model, HttpSession httpSession) {

        if (httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }

        if (!model.containsAttribute("taskAddBindingModel")) {
            model.addAttribute("taskAddBindingModel", new TaskAddBindingModel());
            model.addAttribute("taskNameExistence", false);
        }
        return "add-task";
    }

    @PostMapping("/add")
    public String addTask(@Valid TaskAddBindingModel taskAddBindingModel,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes,
                          HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("taskAddBindingModel", taskAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.taskAddBindingModel", bindingResult);
            return "redirect:/tasks/add";
        }

        if (this.taskService.checkTaskNameIfExist(taskAddBindingModel.getName())) {
            redirectAttributes.addFlashAttribute("taskAddBindingModel", taskAddBindingModel);
            redirectAttributes.addFlashAttribute("taskNameExistence", true);
            return "redirect:/tasks/add";
        }

        UserServiceModel userServiceModel = this.modelMapper.map(httpSession.getAttribute("user"), UserServiceModel.class);
        TaskAddServiceModel serviceModel = this.modelMapper.map(taskAddBindingModel, TaskAddServiceModel.class);

        this.taskService.addTask(serviceModel, userServiceModel);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String progressTask(@PathVariable String id) {

        this.taskService.progress(id);

        return "redirect:/";
    }

}
