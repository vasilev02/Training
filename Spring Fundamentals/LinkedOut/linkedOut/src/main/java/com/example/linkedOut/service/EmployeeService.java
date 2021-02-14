package com.example.linkedOut.service;

import com.example.linkedOut.model.service.EmployeeServiceModel;
import com.example.linkedOut.model.view.EmployeeDetailViewModel;
import com.example.linkedOut.model.view.EmployeeViewModel;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    EmployeeServiceModel addEmployee(EmployeeServiceModel employeeServiceModel);

    boolean checkExistence(String firstName, String lastName);

    boolean checkDate(LocalDate date);

    List<EmployeeViewModel> getEmployeesAsViewModels();

    EmployeeDetailViewModel getEmployeeById(String id);

    void deleteEmployee(String id);
}
