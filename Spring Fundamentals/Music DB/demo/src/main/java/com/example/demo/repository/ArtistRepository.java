package com.example.demo.repository;

import com.example.demo.model.entity.Artist;
import com.example.demo.model.enumeration.ArtistEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,String> {

    Artist findArtistByArtist(ArtistEnum artistEnum);

}
