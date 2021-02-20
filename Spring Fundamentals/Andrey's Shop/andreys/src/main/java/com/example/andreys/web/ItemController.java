package com.example.andreys.web;

import com.example.andreys.model.service.ItemServiceModel;
import com.example.andreys.model.view.ItemDetailsViewModel;
import com.example.andreys.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemController(ItemService itemService, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/item/{id}")
    public String showDetailsPage(@PathVariable String id, Model model){

        ItemServiceModel item = this.itemService.getItemById(id);

        model.addAttribute("item", this.modelMapper.map(item, ItemDetailsViewModel.class));

        return "details-item";
    }

    @GetMapping("/item/delete/{id}")
    public String deleteItem(@PathVariable String id){

        this.itemService.deleteItemById(id);

        return "redirect:/";
    }

}
