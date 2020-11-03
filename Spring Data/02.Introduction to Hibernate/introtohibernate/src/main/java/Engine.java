import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

public class Engine implements Runnable {

    private final EntityManager entityManager;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void run() {

        //this.changeCasing();
        //this.containsEmployee("Svetlin Nakov");
        //this.getEmployeesWithSalary(BigDecimal.valueOf(50000));
        //this.getEmployeesFromDepartment();
        //this.addAddressToEmployee("Nakov");
        //this.findMaximumSalaries();
        //this.increaseSalary();
        //this.findEmployeeByFirstName();
        //this.addressesWithEmployeeCount();
        //this.getEmployeeWithProject(83);
        //this.findTenLatestProjects();
        this.removeTown("Seattle");


    }

    private void removeTown(String townInput) {

        Town town = this.entityManager.createQuery("FROM Town WHERE name = :givenTown",Town.class)
                .setParameter("givenTown",townInput)
                .getSingleResult();

        this.entityManager.getTransaction().begin();

        String employeeQuery = String.format("SELECT employee_id, first_name, last_name\n" +
                "FROM employees\n" +
                "JOIN addresses a on a.address_id = employees.address_id\n" +
                "JOIN towns t on t.town_id = a.town_id\n" +
                "WHERE t.name = '%s';",townInput);

        List<Object[]> resultEmployees = this.entityManager.createNativeQuery(employeeQuery)
                .getResultList();

        resultEmployees.forEach(entry -> {

            this.entityManager.createQuery("UPDATE Employee SET address = null WHERE id = :givenId")
            .setParameter("givenId",entry[0])
            .executeUpdate();

        });

        String deleteAddressesQuery = String.format("DELETE FROM addresses\n" +
                "WHERE town_id = %d;",town.getId());

        this.entityManager.createNativeQuery(deleteAddressesQuery)
                .executeUpdate();

        String deleteTownQuery = String.format("DELETE FROM towns\n" +
                "WHERE town_id = %d;",town.getId());

        this.entityManager.createNativeQuery(deleteTownQuery)
                .executeUpdate();


        this.entityManager.getTransaction().commit();

    }

    private void findTenLatestProjects() {

        this.entityManager.getTransaction().begin();

        List<Object[]> projects = this.entityManager.createNativeQuery("SELECT name, description, start_date, end_date\n" +
                "FROM projects\n" +
                "ORDER BY start_date DESC ,name ASC\n" +
                "LIMIT 10;").getResultList();

        projects.forEach(entry -> {

            System.out.printf("Project name: %s\n" +
                    " \tProject Description: %s\n" +
                    " \tProject Start Date:%s\n" +
                    " \tProject End Date: %s\n",
                    entry[0],
                    entry[1],
                    entry[2],
                    entry[3]);

        });

        this.entityManager.getTransaction().commit();

    }

    private void getEmployeeWithProject(int inputId) {

        this.entityManager.getTransaction().begin();

        Employee employee = this.entityManager.createQuery("FROM Employee WHERE id = :givenInput",Employee.class)
                .setParameter("givenInput",inputId)
                .getSingleResult();


        String query = String.format("SELECT p.name, ep.employee_id\n" +
                "FROM employees\n" +
                "JOIN employees_projects ep on employees.employee_id = ep.employee_id\n" +
                "JOIN projects p on ep.project_id = p.project_id\n" +
                "WHERE first_name = '%s' AND last_name = '%s' ORDER BY p.name;",employee.getFirstName(),employee.getLastName());

        List<Object[]> projects = this.entityManager.createNativeQuery(query)
                .getResultList();

        System.out.printf("%s %s - %s%n",employee.getFirstName(), employee.getLastName(), employee.getJobTitle());

        projects.forEach(entry ->

            System.out.printf("\t%s%n",entry[0])

        );

        this.entityManager.getTransaction().commit();

    }

    private void addressesWithEmployeeCount() {

        this.entityManager.getTransaction().begin();

        List<Object[]> result = this.entityManager.createNativeQuery("SELECT address_text, t.name, COUNT(e.employee_id) AS `count`\n" +
                "FROM addresses\n" +
                "JOIN towns t on t.town_id = addresses.town_id\n" +
                "JOIN employees e on addresses.address_id = e.address_id\n" +
                "GROUP BY addresses.address_id\n" +
                "ORDER BY `count` DESC\n" +
                "LIMIT 10;").getResultList();

        result.forEach(entry ->

                        System.out.printf("%s, %s - %d%n",
                                entry[0],
                                entry[1],
                                entry[2])

                );

        this.entityManager.getTransaction().commit();


    }

    private void findEmployeeByFirstName() {

        this.entityManager.getTransaction().begin();

        List<Object[]> result = this.entityManager.createNativeQuery("SELECT first_name, last_name, job_title, salary\n" +
                "FROM `employees`\n" +
                "WHERE first_name LIKE 'SA%'\n" +
                "ORDER BY first_name ASC;")
                .getResultList();

        result.forEach(entry ->

                        System.out.printf("%s %s - %s - ($%.2f)%n",
                                entry[0],
                                entry[1],
                                entry[2],
                                entry[3])

                );

        this.entityManager.getTransaction().commit();

    }

    private void increaseSalary() {

        this.entityManager.getTransaction().begin();

        this.entityManager.createNativeQuery("UPDATE `employees`\n" +
                "SET salary = salary * 1.12\n" +
                "WHERE department_id IN (4, 11, 1, 2);")
                .executeUpdate();


        List<Object[]> result = this.entityManager.createNativeQuery("SELECT first_name, last_name, salary\n" +
                "FROM `employees`\n" +
                "WHERE department_id IN (4, 11, 1, 2);")
                .getResultList();

        result.forEach(entry ->

                        System.out.printf("%s %s ($%.2f)%n",
                                entry[0],
                                entry[1],
                                entry[2])

                );

        this.entityManager.getTransaction().commit();

    }

    private void findMaximumSalaries() {

        this.entityManager.getTransaction().begin();

        List<Object[]> result = this.entityManager.createNativeQuery("SELECT name, MAX(e.salary) AS `max_salary`\n" +
                "FROM departments\n" +
                "JOIN employees e on departments.department_id = e.department_id\n" +
                "GROUP BY name\n" +
                "HAVING `max_salary` NOT BETWEEN 30000 AND 70000;")
                .getResultList();

        result.forEach(entry -> {

            System.out.printf("%s %.2f%n",entry[0],entry[1]);

        });

        System.out.println();

        this.entityManager.getTransaction().commit();

    }

    private void addAddressToEmployee(String lastNameInput) {

        this.entityManager.getTransaction().begin();

        //Check if address exists

        try {

            Address address = this.entityManager.createQuery("FROM Address WHERE text = 'Vitoshka 15'",Address.class)
                    .getSingleResult();

            this.entityManager.createQuery("UPDATE Employee SET address = :givenAddress WHERE lastName = :givenInput")
                    .setParameter("givenAddress",address)
                    .setParameter("givenInput",lastNameInput)
                    .executeUpdate();

        }catch (Exception e){

            Town town = this.entityManager.createQuery("FROM Town WHERE id = 32",Town.class)
                    .getSingleResult();

            Address address = new Address();

            address.setText("Vitoshka 15");
            address.setTown(town);

            this.entityManager.persist(address);

            this.entityManager.createQuery("UPDATE Employee SET address = :givenAddress WHERE lastName = :givenInput")
                    .setParameter("givenAddress",address)
                    .setParameter("givenInput",lastNameInput)
                    .executeUpdate();

            this.entityManager.flush();

            this.entityManager.getTransaction().commit();
            return;

        }

        this.entityManager.flush();

        this.entityManager.getTransaction().commit();

    }

    private void getEmployeesFromDepartment() {

        this.entityManager.getTransaction().begin();

        List<Employee> employees = this.entityManager.createQuery("FROM Employee WHERE department.name = 'Research and Development' ORDER BY salary ASC, id ASC",Employee.class)
                .getResultList();

        employees.forEach(entry ->
                System.out.printf("%s %s from %s - $%.2f%n",
                        entry.getFirstName(),
                        entry.getLastName(),
                        entry.getDepartment().getName(),
                        entry.getSalary()));

        this.entityManager.getTransaction().commit();

    }

    private void changeCasing() {

        this.entityManager.getTransaction().begin();

        List<Town> towns = this.entityManager.createQuery("FROM Town WHERE CHAR_LENGTH(name) > 5",Town.class)
                .getResultList();

        System.out.println();

        towns.forEach(entry ->
                entry.setName(entry.getName().toLowerCase()));

        this.entityManager.flush();

        this.entityManager.getTransaction().commit();

    }

    private void containsEmployee(String fullName) {

        this.entityManager.getTransaction().begin();

        try {

            Employee employee = this.entityManager.createQuery("FROM Employee WHERE CONCAT(firstName,' ',lastName) = :givenFullName", Employee.class)
                    .setParameter("givenFullName", fullName).getSingleResult();

            System.out.println("Yes");

        } catch (Exception e) {

            System.out.println("No");

        }


        this.entityManager.getTransaction().commit();

    }

    private void getEmployeesWithSalary(BigDecimal inputSalary) {

        this.entityManager.getTransaction().begin();

        List<Employee> employees = this.entityManager
                .createQuery("FROM Employee WHERE salary > :givenSalary", Employee.class)
                .setParameter("givenSalary", inputSalary).getResultList();

        employees.forEach(entry ->
                System.out.println(entry.getFirstName()));

        this.entityManager.getTransaction().commit();

    }

}
