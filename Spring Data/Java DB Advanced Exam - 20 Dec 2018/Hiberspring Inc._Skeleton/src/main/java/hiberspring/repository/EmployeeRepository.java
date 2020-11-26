package hiberspring.repository;

import hiberspring.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    Employee findEmployeeByCard_Number(String number);

    @Query(value = "SELECT e FROM Employee AS e WHERE e.branch IS NOT NULL AND e.branch.products.size > 0 ORDER BY e.firstName ASC ,e.lastName ASC, LENGTH(e.position) DESC")
    Set<Employee> employeesInfo();

}
