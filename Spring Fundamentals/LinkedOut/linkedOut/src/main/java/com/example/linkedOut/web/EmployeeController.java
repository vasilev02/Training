package com.example.linkedOut.web;

import com.example.linkedOut.model.binding.EmployeeAddBindingModel;
import com.example.linkedOut.model.service.EmployeeServiceModel;
import com.example.linkedOut.service.CompanyService;
import com.example.linkedOut.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, CompanyService companyService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.companyService = companyService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String showAddEmployeePage(Model model){

        if(!model.containsAttribute("employeeAddBindingModel")){
            model.addAttribute("employeeAddBindingModel",new EmployeeAddBindingModel());
            model.addAttribute("isExist", false);
            model.addAttribute("isAfter", false);
            model.addAttribute("companies", this.companyService.getAllCompanies());
        }
        return "employee-add";
    }

    @PostMapping("/add")
    public String addEmployee(@Valid EmployeeAddBindingModel employeeAddBindingModel,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("employeeAddBindingModel", employeeAddBindingModel);
            redirectAttributes.addFlashAttribute("companies", this.companyService.getAllCompanies());
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.employeeAddBindingModel", bindingResult);
            return "redirect:/employees/add";
        }

        if(this.employeeService.checkExistence(employeeAddBindingModel.getFirstName(),employeeAddBindingModel.getLastName())){
            redirectAttributes.addFlashAttribute("employeeAddBindingModel", employeeAddBindingModel);
            redirectAttributes.addFlashAttribute("isExist", true);
            return "redirect:/employees/add";
        }

        if(this.employeeService.checkDate(LocalDate.parse(employeeAddBindingModel.getBirthdate()))){
            redirectAttributes.addFlashAttribute("employeeAddBindingModel", employeeAddBindingModel);
            redirectAttributes.addFlashAttribute("companies", this.companyService.getAllCompanies());
            redirectAttributes.addFlashAttribute("isAfter", true);
            return "redirect:/employees/add";
        }

        EmployeeServiceModel employeeServiceModel = this.modelMapper.map(employeeAddBindingModel, EmployeeServiceModel.class);

        this.employeeService.addEmployee(employeeServiceModel);

        return "redirect:/";
    }

}
