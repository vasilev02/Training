package com.example.demo.init;

import com.example.demo.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ArtistDatabaseInitializer implements CommandLineRunner {

    private ArtistService artistService;

    @Autowired
    public ArtistDatabaseInitializer(ArtistService artistService) {
        this.artistService = artistService;
    }

    @Override
    public void run(String... args) throws Exception {
        artistService.populateArtists();
    }
}
