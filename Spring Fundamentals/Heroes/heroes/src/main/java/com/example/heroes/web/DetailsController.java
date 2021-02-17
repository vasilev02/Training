package com.example.heroes.web;

import com.example.heroes.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/heroes")
public class DetailsController {

    private final HeroService heroService;

    @Autowired
    public DetailsController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("/hero/{id}")
    public String showDetailPage(@PathVariable String id, Model model) {
        if (!model.containsAttribute("hero")) {
            model.addAttribute("hero", this.heroService.getHeroById(id));
        }
        return "details-hero";
    }

    @PostMapping("/hero/{id}")
    public String deleteHero(@PathVariable String id) {
        this.heroService.deleteHeroById(id);
        return "redirect:/";
    }

}
