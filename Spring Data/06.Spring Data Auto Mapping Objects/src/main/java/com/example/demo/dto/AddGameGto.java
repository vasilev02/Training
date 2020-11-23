package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddGameGto {

    private String title;
    private BigDecimal price;
    private double size;
    private String trailer;
    private String imageThumbNail;
    private String description;
    private LocalDate releaseDate;

    public AddGameGto() {
    }

    public AddGameGto(String title, BigDecimal price, double size, String trailer, String imageThumbNail, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.imageThumbNail = imageThumbNail;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImageThumbNail() {
        return imageThumbNail;
    }

    public void setImageThumbNail(String imageThumbNail) {
        this.imageThumbNail = imageThumbNail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
