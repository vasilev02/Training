package com.example.demo.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private int age;
    private Set<User> friends;
    private Set<Product> seller;
    private Set<Product> buyer;

    public User() {
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @ManyToMany
    @JoinTable(name = "users_friends",
    joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "friend_id",referencedColumnName = "id"))
    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @OneToMany(mappedBy = "seller",targetEntity = Product.class,cascade = CascadeType.ALL,
    fetch = FetchType.EAGER)
    public Set<Product> getSeller() {
        return seller;
    }

    public void setSeller(Set<Product> seller) {
        this.seller = seller;
    }

    @OneToMany(mappedBy = "buyer",targetEntity = Product.class,cascade = CascadeType.ALL,
    fetch = FetchType.EAGER)
    public Set<Product> getBuyer() {
        return buyer;
    }

    public void setBuyer(Set<Product> buyer) {
        this.buyer = buyer;
    }
}
