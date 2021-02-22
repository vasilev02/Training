package com.example.demo.web;

import com.example.demo.model.binding.AlbumAddBindingModel;
import com.example.demo.model.entity.User;
import com.example.demo.model.service.AlbumAddServiceModel;
import com.example.demo.model.service.UserServiceModel;
import com.example.demo.service.AlbumService;
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
@RequestMapping("/album")
public class AlbumController {

    private final AlbumService albumService;
    private final ModelMapper modelMapper;

    @Autowired
    public AlbumController(AlbumService albumService, ModelMapper modelMapper) {
        this.albumService = albumService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String showAddAlbumPage(Model model, HttpSession httpSession){

        if(httpSession.getAttribute("user") == null){
            return "redirect:/";
        }

        if(!model.containsAttribute("albumAddBindingModel")){
            model.addAttribute("albumAddBindingModel", new AlbumAddBindingModel());
        }

        return "add-album";
    }

    @PostMapping("/add")
    public String addAlbum(@Valid AlbumAddBindingModel albumAddBindingModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession httpSession){

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("albumAddBindingModel", albumAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.albumAddBindingModel", bindingResult);
            return "redirect:/album/add";
        }

        UserServiceModel user = this.modelMapper.map(httpSession.getAttribute("user"), UserServiceModel.class);

        AlbumAddServiceModel serviceModel = this.modelMapper.map(albumAddBindingModel, AlbumAddServiceModel.class);

        serviceModel.setUser(user);

        this.albumService.addProduct(serviceModel);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteAlbum(@PathVariable String id, HttpSession httpSession){

        if(httpSession.getAttribute("user") == null){
            return "redirect:/";
        }

        this.albumService.deleteAlbum(id);
        return "redirect:/";
    }

}
