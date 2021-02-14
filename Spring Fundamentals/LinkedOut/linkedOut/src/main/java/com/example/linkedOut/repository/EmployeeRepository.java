package com.example.linkedOut.repository;

import com.example.linkedOut.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    Optional<Employee> findEmployeeByFirstNameAndLastName(String firstName, String LastName);

    @Query(value = "SELECT e FROM Employee AS e ORDER BY e.firstName, e.lastName ASC")
    List<Employee> getAllEmployeesFromDB();

    Employee findEmployeeById(String id);

}
