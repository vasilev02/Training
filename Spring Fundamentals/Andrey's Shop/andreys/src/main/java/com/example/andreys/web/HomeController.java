package com.example.andreys.web;

import com.example.andreys.model.view.ItemHomeViewModel;
import com.example.andreys.service.ItemService;
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

    private final ItemService itemService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(ItemService itemService, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String showHomePage(HttpSession httpSession, Model model){

        if(httpSession.getAttribute("user") != null){

            List<ItemHomeViewModel> viewModels = this.itemService.getItems().stream().map(serviceModel -> {
                return this.modelMapper.map(serviceModel, ItemHomeViewModel.class);
            }).collect(Collectors.toList());

            model.addAttribute("items", viewModels);

            return "home";
        }
        return "index";
    }

}
