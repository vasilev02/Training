package com.example.demo.model.view;

import com.example.demo.model.entity.Artist;
import com.example.demo.model.enumeration.ArtistEnum;
import com.example.demo.model.enumeration.GenreEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumViewModel {

    private String id;
    private String name;
    private ArtistEnum artist;
    private String genre;
    private String price;
    private String releaseDate;
    private String copies;
    private String imageUrl;

    public AlbumViewModel() {
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

    public ArtistEnum getArtist() {
        return artist;
    }

    public void setArtist(ArtistEnum artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCopies() {
        return copies;
    }

    public void setCopies(String copies) {
        this.copies = copies;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
