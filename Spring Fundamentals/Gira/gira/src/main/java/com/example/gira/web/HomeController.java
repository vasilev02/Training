package com.example.gira.web;

import com.example.gira.model.view.TaskHomeViewModel;
import com.example.gira.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final TaskService taskService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(TaskService taskService, ModelMapper modelMapper) {
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String showHomePage(HttpSession httpSession, Model model) {
        if (httpSession.getAttribute("user") != null) {

            List<TaskHomeViewModel> viewModels = this.taskService.getAllTasks().stream().map(t -> {
                return this.modelMapper.map(t, TaskHomeViewModel.class);
            }).collect(Collectors.toList());

            model.addAttribute("tasks", viewModels);
            return "home";
        }
        return "index";
    }

}
