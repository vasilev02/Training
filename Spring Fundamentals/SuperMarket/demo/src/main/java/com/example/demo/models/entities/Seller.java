package com.example.demo.models.entities;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "sellers")
public class Seller extends BaseEntity {

    private String firstName;
    private String lastName;
    private int age;
    private BigDecimal salary;
    private Shop shop;
    private Seller manager;

    public Seller() {
    }

    @Column(name = "first_name", nullable = false)
    @Size(min = 2, max = 20)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    @Size(min = 2, max = 20)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "age", nullable = false)
    @Min(value = 0)
    @Max(value = 100)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "salary", nullable = false)
    @Min(value = 0)
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    public Seller getManager() {
        return manager;
    }

    public void setManager(Seller manager) {
        this.manager = manager;
    }
}
