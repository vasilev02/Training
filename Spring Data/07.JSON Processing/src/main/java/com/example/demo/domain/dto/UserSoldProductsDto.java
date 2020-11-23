package com.example.demo.domain.dto;


import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class UserSoldProductsDto implements Serializable {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private int age;

    @Expose
    private SoldDto soldProducts;

    public UserSoldProductsDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SoldDto getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(SoldDto soldProducts) {
        this.soldProducts = soldProducts;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}