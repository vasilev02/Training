package com.example.linkedOut.web;

import com.example.linkedOut.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeDetailController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeDetailController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/{id}")
    public String showEmployeeDetailPage(@PathVariable String id, Model model){

        if(!model.containsAttribute("employee")){
            model.addAttribute("employee", this.employeeService.getEmployeeById(id));
        }
        return "employee-details";
    }

    @PostMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable String id){

        this.employeeService.deleteEmployee(id);
        return "redirect:/";
    }

}
