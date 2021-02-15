package com.example.shop.web;

import com.example.shop.model.binding.ProductAddBindingModel;
import com.example.shop.model.service.ProductAddServiceModel;
import com.example.shop.service.ProductService;
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

import javax.validation.Valid;

@Controller
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String showAddProductPage(Model model) {
        if (!model.containsAttribute("productAddBindingModel")) {
            model.addAttribute("productAddBindingModel", new ProductAddBindingModel());
            model.addAttribute("isExists", false);
        }
        return "product-add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid ProductAddBindingModel productAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel", bindingResult);
            return "redirect:/products/add";
        }

        if(this.productService.checkProductIfExistByName(productAddBindingModel.getName())){
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute("isExists", true);
            return "redirect:/products/add";
        }


        ProductAddServiceModel serviceModel = this.modelMapper.map(productAddBindingModel, ProductAddServiceModel.class);

        this.productService.addProduct(serviceModel);

        return "redirect:/";
    }

    @GetMapping("/buy/{id}")
    public String addProduct(@PathVariable String id) {
        this.productService.buyProduct(id);
        return "redirect:/";
    }

    @GetMapping("/buy/all")
    public String addProduct() {
        this.productService.buyAllProducts();
        return "redirect:/";
    }

}
