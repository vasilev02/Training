package com.example.demo.service;

import com.example.demo.model.entity.Artist;
import com.example.demo.model.enumeration.ArtistEnum;

public interface ArtistService {
    void populateArtists();

    Artist getArtist(ArtistEnum artist);
}
