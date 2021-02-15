package com.example.shop.web;

import com.example.shop.model.enumeration.CategoryEnum;
import com.example.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final ProductService productService;

    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String showHomePage(Model model, HttpSession httpSession) {

        if (httpSession.getAttribute("user") == null) {
            return "index";
        }

        model.addAttribute("food", this.productService.getProductsByCategory(CategoryEnum.FOOD));
        model.addAttribute("drinks", this.productService.getProductsByCategory(CategoryEnum.DRINK));
        model.addAttribute("households", this.productService.getProductsByCategory(CategoryEnum.HOUSEHOLD));
        model.addAttribute("others", this.productService.getProductsByCategory(CategoryEnum.OTHER));

        model.addAttribute("totalSum", this.productService.getProductsSum());

        return "home";
    }

}
