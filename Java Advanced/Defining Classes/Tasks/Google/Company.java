package google;

public class Company {
    private String companyName;
    private String department;
    private String salary;

    public Company(String companyName, String department, String salary) {
        this.companyName = companyName;
        this.department = department;
        this.salary = salary;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getDepartment() {
        return department;
    }

    public String getSalary() {
        return salary;
    }
}
