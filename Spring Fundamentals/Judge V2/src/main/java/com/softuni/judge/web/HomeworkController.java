package com.softuni.judge.web;

import com.softuni.judge.model.binding.HomeworkCheckBindingModel;
import com.softuni.judge.model.binding.HomeworkAddBindingModel;
import com.softuni.judge.model.service.HomeworkAddCommentServiceModel;
import com.softuni.judge.model.service.HomeworkCheckServiceModel;
import com.softuni.judge.model.view.HomeworkCheckViewModel;
import com.softuni.judge.security.CurrentUser;
import com.softuni.judge.service.CommentService;
import com.softuni.judge.service.ExerciseService;
import com.softuni.judge.service.HomeworkService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/homeworks")
public class HomeworkController {

    private final ExerciseService exerciseService;
    private final HomeworkService homeworkService;
    private final CommentService commentService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeworkController(ExerciseService exerciseService, HomeworkService homeworkService, CommentService commentService, CurrentUser currentUser, ModelMapper modelMapper) {
        this.exerciseService = exerciseService;
        this.homeworkService = homeworkService;
        this.commentService = commentService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String showAddHomeworkPage(Model model) {

        if (currentUser.isAnonymous()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("homeworkAddBindingModel")) {
            model.addAttribute("homeworkAddBindingModel", new HomeworkAddBindingModel());
            model.addAttribute("isLate", false);
        }
        model.addAttribute("exercises", this.exerciseService.getAllExercises());
        return "homework-add";
    }

    @GetMapping("/check")
    public String showCheckHomeworkPage(Model model) {

        if (currentUser.isAnonymous()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("homeworkCheckBindingModel")) {
            model.addAttribute("homeworkCheckBindingModel", new HomeworkCheckBindingModel());
        }

        HomeworkCheckServiceModel serviceModel = homeworkService.getHomeworkToCheck();
        if(serviceModel == null){
            model.addAttribute("homework", null);
        }else{
            HomeworkCheckViewModel viewModel = this.modelMapper.map(serviceModel, HomeworkCheckViewModel.class);
            model.addAttribute("homework", viewModel);
        }
        return "homework-check";
    }

    @PostMapping("/add")
    public String addHomework(@Valid HomeworkAddBindingModel homeworkAddBindingModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("homeworkAddBindingModel", homeworkAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.homeworkAddBindingModel", bindingResult);

            return "redirect:/homeworks/add";
        }

        boolean checkLate = this.exerciseService.checkExpireDate(homeworkAddBindingModel.getExercise());

        if (checkLate) {
            this.homeworkService.addHomework(homeworkAddBindingModel);

            return "redirect:/";
        }

        redirectAttributes.addFlashAttribute("homeworkAddBindingModel", homeworkAddBindingModel);
        redirectAttributes.addFlashAttribute("isLate", true);
        return "redirect:/homeworks/add";
    }

    @PostMapping("/check")
    public String checkHomework(@Valid HomeworkCheckBindingModel homeworkCheckBindingModel,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes, @RequestParam String id) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("homeworkCheckBindingModel", homeworkCheckBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.homeworkCheckBindingModel", bindingResult);
            return "redirect:/homeworks/check";
        }

        HomeworkAddCommentServiceModel serviceModel = this.modelMapper.map(homeworkCheckBindingModel, HomeworkAddCommentServiceModel.class);

        this.commentService.addComment(serviceModel, id);

        return "redirect:/";
    }

}
