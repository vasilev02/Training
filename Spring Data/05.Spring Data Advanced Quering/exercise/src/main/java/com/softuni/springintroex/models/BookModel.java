package com.softuni.springintroex.models;

import com.softuni.springintroex.entities.AgeRestriction;
import com.softuni.springintroex.entities.EditionType;

import java.math.BigDecimal;

public class BookModel {

    private String title;
    private EditionType editionType;
    private AgeRestriction ageRestriction;
    private BigDecimal price;

    public BookModel(String title, EditionType editionType, AgeRestriction ageRestriction, BigDecimal price) {
        this.title = title;
        this.editionType = editionType;
        this.ageRestriction = ageRestriction;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }


    public EditionType getEditionType() {
        return editionType;
    }


    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }


    public BigDecimal getPrice() {
        return price;
    }

}
