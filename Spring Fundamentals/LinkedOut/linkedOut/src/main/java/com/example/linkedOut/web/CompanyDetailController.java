package com.example.linkedOut.web;

import com.example.linkedOut.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/companies")
public class CompanyDetailController {

    private final CompanyService companyService;

    @Autowired
    public CompanyDetailController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/company/{id}")
    public String showCompanyDetail(@PathVariable String id, Model model) {

        if (!model.containsAttribute("company")) {
            model.addAttribute("company", this.companyService.getCompanyById(id));
        }
        return "company-details";
    }

    @PostMapping("/company/{id}")
    public String deleteCompany(@PathVariable String id){
        this.companyService.deleteCompany(id);
        return "redirect:/";
    }

}
