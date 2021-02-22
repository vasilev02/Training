package com.example.demo.service;

import com.example.demo.model.service.AlbumAddServiceModel;
import com.example.demo.model.view.AlbumViewModel;

import java.util.List;

public interface AlbumService {

    AlbumAddServiceModel addProduct(AlbumAddServiceModel serviceModel);

    List<AlbumViewModel> getAllAlbums();

    Long getCopiesCount();

    void deleteAlbum(String id);
}
