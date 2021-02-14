package com.example.linkedOut.model.binding;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;

public class EmployeeAddBindingModel {

    private String firstName;
    private String lastName;
    private String birthdate;
    private String educationLevel;
    private String jobTitle;
    private String salary;
    private String company;

    public EmployeeAddBindingModel() {
    }

    @NotNull
    @Size(min = 2, message = "First name must be at least 2 characters")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Size(min = 2, message = "Last name must be at least 2 characters")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "Type your birthdate")
    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @NotNull(message = "Choose education level")
    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    @NotNull(message = "Type your job title")
    @Size(min = 1, message = "Type your job title")
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @NotNull
    @Positive(message = "Salary must be positive")
    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @NotNull(message = "Choose company")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
