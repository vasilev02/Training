package com.softuni.judge.web;

import com.softuni.judge.security.CurrentUser;
import com.softuni.judge.service.CommentService;
import com.softuni.judge.service.ExerciseService;
import com.softuni.judge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final ExerciseService exerciseService;
    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public HomeController(CurrentUser currentUser, ExerciseService exerciseService, CommentService commentService, UserService userService) {
        this.currentUser = currentUser;
        this.exerciseService = exerciseService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("activeExercises", this.exerciseService.getAllExercises());
        model.addAttribute("usersCount", this.userService.getUsernames().size());

        model.addAttribute("six", this.commentService.getGradesFor(6));
        model.addAttribute("five", this.commentService.getGradesFor(5));
        model.addAttribute("four", this.commentService.getGradesFor(4));
        model.addAttribute("three", this.commentService.getGradesFor(3));
        model.addAttribute("two", this.commentService.getGradesFor(2));

        return currentUser.isAnonymous() ? "index" : "home";
    }


}
