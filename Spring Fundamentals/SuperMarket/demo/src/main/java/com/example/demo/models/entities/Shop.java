package com.example.demo.models.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "shops")
public class Shop extends BaseEntity {

    private String name;
    private String address;
    private Town town;
    private Set<Seller> sellers;
    private Set<Product> products;

    public Shop() {
    }

    @Column(name = "name", nullable = false, unique = true)
    @Size(min = 2, max = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "address", nullable = false, unique = true)
    @Size(min = 2, max = 20)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ManyToOne
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @OneToMany(mappedBy = "shop", targetEntity = Seller.class)
    public Set<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(Set<Seller> sellers) {
        this.sellers = sellers;
    }

    @ManyToMany(mappedBy = "shops", targetEntity = Product.class, fetch = FetchType.EAGER)
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
