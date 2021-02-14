package com.example.linkedOut.web;

import com.example.linkedOut.model.binding.CompanyAddBindingModel;
import com.example.linkedOut.model.service.CompanyServiceModel;
import com.example.linkedOut.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyController(CompanyService companyService, ModelMapper modelMapper) {
        this.companyService = companyService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String showAddCompanyPage(Model model) {
        if (!model.containsAttribute("companyAddBindingModel")) {
            model.addAttribute("companyAddBindingModel", new CompanyAddBindingModel());
            model.addAttribute("isExist", false);
        }
        return "company-add";
    }



    @PostMapping("/add")
    public String addCompany(@Valid CompanyAddBindingModel companyAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("companyAddBindingModel", companyAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.companyAddBindingModel", bindingResult);
            return "redirect:/companies/add";
        }

        if (this.companyService.checkExistence(companyAddBindingModel.getName())) {
            redirectAttributes.addFlashAttribute("companyAddBindingModel", companyAddBindingModel);
            redirectAttributes.addFlashAttribute("isExist", true);
            return "redirect:/companies/add";
        }

        CompanyServiceModel companyServiceModel = this.modelMapper.map(companyAddBindingModel, CompanyServiceModel.class);

        this.companyService.addCompany(companyServiceModel);

        return "redirect:/";
    }


}
