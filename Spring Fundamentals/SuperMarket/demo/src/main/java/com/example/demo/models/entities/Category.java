package com.example.demo.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    private String name;
    private Set<Product> products;

    public Category() {
    }

    @Column(name = "name", nullable = false, unique = true)
    @Size(min = 2, max = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "category", targetEntity = Product.class)
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
