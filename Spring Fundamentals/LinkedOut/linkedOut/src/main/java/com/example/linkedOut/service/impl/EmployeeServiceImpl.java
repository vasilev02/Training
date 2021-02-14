package com.example.linkedOut.service.impl;

import com.example.linkedOut.model.entity.Company;
import com.example.linkedOut.model.entity.Employee;
import com.example.linkedOut.model.service.EmployeeServiceModel;
import com.example.linkedOut.model.view.EmployeeDetailViewModel;
import com.example.linkedOut.model.view.EmployeeViewModel;
import com.example.linkedOut.repository.EmployeeRepository;
import com.example.linkedOut.service.CompanyService;
import com.example.linkedOut.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyService companyService, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.companyService = companyService;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeServiceModel addEmployee(EmployeeServiceModel employeeServiceModel) {

        Employee employee = this.modelMapper.map(employeeServiceModel, Employee.class);

        Company company = this.companyService.findCompanyByName(employeeServiceModel.getCompany()).orElse(null);

        employee.setCompany(company);
        employee.setSalary(new BigDecimal(employeeServiceModel.getSalary()));
        employee.setBirthdate(LocalDate.parse(employeeServiceModel.getBirthdate()));

        this.employeeRepository.saveAndFlush(employee);

        return employeeServiceModel;
    }

    @Override
    public boolean checkExistence(String firstName, String lastName) {
        return this.employeeRepository.findEmployeeByFirstNameAndLastName(firstName, lastName).isPresent();
    }

    @Override
    public boolean checkDate(LocalDate date) {
        if (date.isAfter(LocalDate.now())) {
            return true;
        }
        return false;
    }

    @Override
    public List<EmployeeViewModel> getEmployeesAsViewModels() {
        return this.employeeRepository.getAllEmployeesFromDB().stream().map(employee -> {
            EmployeeViewModel viewModel = this.modelMapper.map(employee, EmployeeViewModel.class);


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            LocalDate newDate = LocalDate.parse(viewModel.getBirthdate());
            String formatDate = newDate.format(formatter);
            viewModel.setBirthdate(formatDate);

            return viewModel;
        }).collect(Collectors.toList());
    }

    @Override
    public EmployeeDetailViewModel getEmployeeById(String id) {

        Employee employee = this.employeeRepository.findEmployeeById(id);

        EmployeeDetailViewModel viewModel = this.modelMapper.map(employee, EmployeeDetailViewModel.class);

        viewModel.setCompany(employee.getCompany().getName());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate newDate = LocalDate.parse(viewModel.getBirthdate());
        String formatDate = newDate.format(formatter);
        viewModel.setBirthdate(formatDate);

        return viewModel;
    }

    @Override
    public void deleteEmployee(String id) {
        Employee employee = this.employeeRepository.findEmployeeById(id);
        employee.setCompany(null);
        this.employeeRepository.saveAndFlush(employee);
        this.employeeRepository.delete(employee);
    }
}
