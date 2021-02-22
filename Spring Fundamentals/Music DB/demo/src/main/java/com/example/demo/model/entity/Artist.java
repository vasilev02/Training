package com.example.demo.model.entity;

import com.example.demo.model.enumeration.ArtistEnum;

import javax.persistence.*;

@Entity
@Table(name = "artists")
public class Artist extends BaseEntity{

    private ArtistEnum artist;
    private String careerInformation;

    public Artist() {
    }

    public Artist(ArtistEnum artist) {
        this.artist = artist;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "artist", nullable = false, unique = true)
    public ArtistEnum getArtist() {
        return artist;
    }

    public void setArtist(ArtistEnum artist) {
        this.artist = artist;
    }

    @Column(name = "career_information", columnDefinition = "TEXT")
    public String getCareerInformation() {
        return careerInformation;
    }

    public void setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
    }

}
