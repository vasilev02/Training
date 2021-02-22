package com.example.demo.repository;

import com.example.demo.model.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album,String> {

    @Query(value = "SELECT a FROM Album AS a ORDER BY a.copies DESC")
    List<Album> getAll();

    @Query(value = "SELECT SUM(a.copies) FROM  Album AS a")
    Long getCopies();

    Album findAlbumById(String id);
}
