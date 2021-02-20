package com.example.andreys.service;

import com.example.andreys.model.service.ItemAddServiceModel;
import com.example.andreys.model.service.ItemServiceModel;

import java.util.List;

public interface ItemService {
    ItemAddServiceModel addItem(ItemAddServiceModel serviceModel);

    boolean checkItemName(String name);

    List<ItemServiceModel> getItems();

    ItemServiceModel getItemById(String id);

    void deleteItemById(String id);
}
