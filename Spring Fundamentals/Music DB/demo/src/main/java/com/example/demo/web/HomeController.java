package com.example.demo.web;

import com.example.demo.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final AlbumService albumService;

    @Autowired
    public HomeController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/")
    public String showHomePage(HttpSession httpSession, Model model){

        if(httpSession.getAttribute("user") != null){
            model.addAttribute("albums", this.albumService.getAllAlbums());
            model.addAttribute("totalCopies", this.albumService.getCopiesCount());
            return "home";
        }
        return "index";
    }

}
