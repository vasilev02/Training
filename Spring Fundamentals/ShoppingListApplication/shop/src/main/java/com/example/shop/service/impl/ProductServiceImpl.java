package com.example.shop.service.impl;

import com.example.shop.model.entity.Category;
import com.example.shop.model.entity.Product;
import com.example.shop.model.enumeration.CategoryEnum;
import com.example.shop.model.service.ProductAddServiceModel;
import com.example.shop.model.view.ProductViewModel;
import com.example.shop.repository.ProductRepository;
import com.example.shop.service.CategoryService;
import com.example.shop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductAddServiceModel addProduct(ProductAddServiceModel serviceModel) {

        Product product = this.modelMapper.map(serviceModel, Product.class);

        Category category = this.categoryService.getCategoryByName(serviceModel.getCategory());
        product.setCategory(category);

        this.productRepository.saveAndFlush(product);
        return serviceModel;
    }

    @Override
    public BigDecimal getProductsSum() {
        BigDecimal totalPrice = this.productRepository.getTotalPrice();
        if(totalPrice == null){
            totalPrice = BigDecimal.valueOf(0);
        }
        return totalPrice;
    }

    @Override
    public List<ProductViewModel> getProductsByCategory(CategoryEnum categoryEnum) {

        return this.productRepository.findProductByCategory_Name(categoryEnum).stream().map(product -> {
            return this.modelMapper.map(product, ProductViewModel.class);
        }).collect(Collectors.toList());

    }

    @Override
    public void buyAllProducts() {
        this.productRepository.deleteAll();
    }

    @Override
    public void buyProduct(String id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public boolean checkProductIfExistByName(String name) {
        return this.productRepository.findProductByName(name).isPresent();
    }
}
