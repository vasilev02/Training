package com.example.andreys.web;

import com.example.andreys.model.binding.ItemAddBindingModel;
import com.example.andreys.model.service.ItemAddServiceModel;
import com.example.andreys.model.view.ItemHomeViewModel;
import com.example.andreys.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/items")
public class ProductController {

    private final ItemService itemService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ItemService itemService, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String showAddItemPage(HttpSession httpSession, Model model) {

        if (httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }

        if (!model.containsAttribute("itemAddBindingModel")) {
            model.addAttribute("itemAddBindingModel", new ItemAddBindingModel());
            model.addAttribute("existingName", false);
        }
        return "add-item";
    }

    @PostMapping("/add")
    public String addItem(@Valid ItemAddBindingModel itemAddBindingModel,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("itemAddBindingModel", itemAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.itemAddBindingModel", bindingResult);
            return "redirect:/items/add";
        }

        if (this.itemService.checkItemName(itemAddBindingModel.getName())) {
            redirectAttributes.addFlashAttribute("itemAddBindingModel", itemAddBindingModel);
            redirectAttributes.addFlashAttribute("existingName", true);
            return "redirect:/items/add";
        }

        ItemAddServiceModel serviceModel = this.modelMapper.map(itemAddBindingModel, ItemAddServiceModel.class);

        this.itemService.addItem(serviceModel);

        return "redirect:/";
    }

}
