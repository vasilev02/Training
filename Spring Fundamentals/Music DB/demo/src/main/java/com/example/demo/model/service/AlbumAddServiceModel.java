package com.example.demo.model.service;

import com.example.demo.model.enumeration.ArtistEnum;
import com.example.demo.model.enumeration.GenreEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumAddServiceModel {

    private String name;
    private String imageUrl;
    private BigDecimal price;
    private long copies;
    private LocalDate releaseDate;
    private String producer;
    private ArtistEnum artist;
    private GenreEnum genre;
    private String description;
    private UserServiceModel user;

    public AlbumAddServiceModel() {
    }

    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(min = 5, message = "Image Url length must be minimum 5 characters")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Positive(message = "Price must be positive")
    @NotNull(message = "Type item price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Min(value = 10, message = "Must be a more than 10 copies")
    public long getCopies() {
        return copies;
    }

    public void setCopies(long copies) {
        this.copies = copies;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Date and Time, that cannot be in the future")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @NotNull(message = "Choose artist")
    public ArtistEnum getArtist() {
        return artist;
    }

    public void setArtist(ArtistEnum artist) {
        this.artist = artist;
    }

    @NotNull(message = "Choose genre")
    public GenreEnum getGenre() {
        return genre;
    }

    public void setGenre(GenreEnum genre) {
        this.genre = genre;
    }

    @Size(min = 5, message = "Description min length must be minimum 5 characters")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }
}
