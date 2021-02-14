package com.example.linkedOut.web;

import com.example.linkedOut.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class AllEmployeesController {

    private final EmployeeService employeeService;

    @Autowired
    public AllEmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public String showAllEmployeesPage(Model model){
        if(!model.containsAttribute("employees")){
            model.addAttribute("employees",this.employeeService.getEmployeesAsViewModels());
        }
        return "employee-all";
    }

}
