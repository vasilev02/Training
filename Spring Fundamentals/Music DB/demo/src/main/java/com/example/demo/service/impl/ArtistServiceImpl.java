package com.example.demo.service.impl;

import com.example.demo.model.entity.Artist;
import com.example.demo.model.enumeration.ArtistEnum;
import com.example.demo.repository.ArtistRepository;
import com.example.demo.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public void populateArtists() {
        if(this.artistRepository.count() == 0){
            ArtistEnum[] artistEnums = ArtistEnum.values();

            for (ArtistEnum current : artistEnums) {
                this.artistRepository.saveAndFlush(new Artist(current));
            }
        }
    }

    @Override
    public Artist getArtist(ArtistEnum artist) {
        return this.artistRepository.findArtistByArtist(artist);
    }
}
