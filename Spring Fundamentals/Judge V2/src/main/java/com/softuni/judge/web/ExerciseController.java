package com.softuni.judge.web;

import com.softuni.judge.model.binding.ExerciseAddBindingModel;
import com.softuni.judge.model.service.ExerciseServiceModel;
import com.softuni.judge.security.CurrentUser;
import com.softuni.judge.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    @Autowired
    public ExerciseController(ExerciseService exerciseService, CurrentUser currentUser, ModelMapper modelMapper) {
        this.exerciseService = exerciseService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String showAddExercisePage(Model model) {

        if (currentUser.isAnonymous()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("exerciseAddBindingModel")) {
            model.addAttribute("exerciseAddBindingModel", new ExerciseAddBindingModel());
        }
        return "exercise-add";
    }

    @PostMapping("/add")
    public String addExercise(@Valid ExerciseAddBindingModel exerciseAddBindingModel,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("exerciseAddBindingModel", exerciseAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.exerciseAddBindingModel", bindingResult);
            return "redirect:/exercises/add";
        }

        ExerciseServiceModel serviceModel = this.modelMapper.map(exerciseAddBindingModel, ExerciseServiceModel.class);

        this.exerciseService.addExercise(serviceModel);

        return "redirect:/";
    }

}
