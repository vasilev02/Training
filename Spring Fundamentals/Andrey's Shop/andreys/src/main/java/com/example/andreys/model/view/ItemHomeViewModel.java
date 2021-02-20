package com.example.andreys.model.view;

public class ItemHomeViewModel {

    private String id;
    private String name;
    private String price;
    private String gender;
    private String categoryEnum;

    public ItemHomeViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategoryEnum() {
        return categoryEnum;
    }

    public void setCategoryEnum(String categoryEnum) {
        this.categoryEnum = categoryEnum;
    }
}
