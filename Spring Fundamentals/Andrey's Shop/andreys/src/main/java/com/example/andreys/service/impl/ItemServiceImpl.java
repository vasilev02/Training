package com.example.andreys.service.impl;

import com.example.andreys.model.entity.Category;
import com.example.andreys.model.entity.Item;
import com.example.andreys.model.service.ItemAddServiceModel;
import com.example.andreys.model.service.ItemServiceModel;
import com.example.andreys.repository.ItemRepository;
import com.example.andreys.service.CategoryService;
import com.example.andreys.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ItemAddServiceModel addItem(ItemAddServiceModel serviceModel) {

        Item item = this.modelMapper.map(serviceModel, Item.class);

        Category category = this.categoryService.getCategoryByName(serviceModel.getCategory());
        item.setCategory(category);

        this.itemRepository.saveAndFlush(item);
        return serviceModel;
    }

    @Override
    public boolean checkItemName(String name) {
        return this.itemRepository.findItemByName(name).isPresent();
    }

    @Override
    public List<ItemServiceModel> getItems() {
         return this.itemRepository.getItems().stream().map(item -> this.modelMapper.map(item, ItemServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public ItemServiceModel getItemById(String id) {

        Item item = this.itemRepository.getItemById(id);
        return this.modelMapper.map(item, ItemServiceModel.class);
    }

    @Override
    public void deleteItemById(String id) {
        this.itemRepository.deleteById(id);
    }
}
