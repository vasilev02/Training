package com.example.linkedOut.web;

import com.example.linkedOut.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/companies")
public class AllCompaniesController {

    private final CompanyService companyService;

    @Autowired
    public AllCompaniesController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/all")
    public String showAllCompaniesPage(Model model){
        if(!model.containsAttribute("companies")){
            model.addAttribute("companies",this.companyService.getAllCompaniesAsViewModels());
        }
        return "company-all";
    }


}
