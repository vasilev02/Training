package com.example.heroes.web;

import com.example.heroes.model.binding.HeroAddBindingModel;
import com.example.heroes.model.service.HeroAddServiceModel;
import com.example.heroes.service.HeroService;
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
@RequestMapping("/heroes")
public class CreateHeroController {

    private final HeroService heroService;
    private final ModelMapper modelMapper;

    @Autowired
    public CreateHeroController(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public String showCreateHeroPage(Model model){
        if(!model.containsAttribute("heroAddBindingModel")){
            model.addAttribute("heroAddBindingModel", new HeroAddBindingModel());
            model.addAttribute("existingHeroName", false);
        }
        return "create-hero";
    }

    @PostMapping("/create")
    public String createHero(@Valid HeroAddBindingModel heroAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("heroAddBindingModel", heroAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.heroAddBindingModel", bindingResult);
            return "redirect:/heroes/create";
        }

        if(this.heroService.checkHeroName(heroAddBindingModel.getHeroName())){
            redirectAttributes.addFlashAttribute("heroAddBindingModel", heroAddBindingModel);
            redirectAttributes.addFlashAttribute("existingHeroName", true);
            return "redirect:/heroes/create";
        }

        HeroAddServiceModel serviceModel = this.modelMapper.map(heroAddBindingModel, HeroAddServiceModel.class);

        this.heroService.addHero(serviceModel);

        return "redirect:/";
    }

}
