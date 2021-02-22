package com.example.demo.service.impl;

import com.example.demo.model.entity.Album;
import com.example.demo.model.entity.Artist;
import com.example.demo.model.entity.User;
import com.example.demo.model.service.AlbumAddServiceModel;
import com.example.demo.model.view.AlbumViewModel;
import com.example.demo.repository.AlbumRepository;
import com.example.demo.service.AlbumService;
import com.example.demo.service.ArtistService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistService artistService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository, ArtistService artistService, UserService userService, ModelMapper modelMapper) {
        this.albumRepository = albumRepository;
        this.artistService = artistService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public AlbumAddServiceModel addProduct(AlbumAddServiceModel serviceModel) {

        User user = this.userService.findUserByUsername(serviceModel.getUser().getUsername());
        Artist artist = this.artistService.getArtist(serviceModel.getArtist());

        Album album = this.modelMapper.map(serviceModel, Album.class);

        album.setUser(user);
        album.setArtist(artist);

        this.albumRepository.saveAndFlush(album);

        return serviceModel;
    }

    @Override
    public List<AlbumViewModel> getAllAlbums() {
        return this.albumRepository.getAll().stream().map(a -> {
            return this.modelMapper.map(a, AlbumViewModel.class);
        }).collect(Collectors.toList());
    }

    @Override
    public Long getCopiesCount() {
        Long copies = this.albumRepository.getCopies();

        if(copies == null){
            return Long.valueOf(0);
        }
        return copies;
    }

    @Override
    public void deleteAlbum(String id) {
        Album album = this.albumRepository.findAlbumById(id);

        album.setArtist(null);
        album.setUser(null);

        this.albumRepository.delete(album);
    }
}
