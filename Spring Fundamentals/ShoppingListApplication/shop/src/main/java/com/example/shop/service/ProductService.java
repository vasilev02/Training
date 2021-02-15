package com.example.shop.service;

import com.example.shop.model.entity.Product;
import com.example.shop.model.enumeration.CategoryEnum;
import com.example.shop.model.service.ProductAddServiceModel;
import com.example.shop.model.view.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    ProductAddServiceModel addProduct(ProductAddServiceModel serviceModel);

    BigDecimal getProductsSum();

    List<ProductViewModel> getProductsByCategory(CategoryEnum food);

    void buyAllProducts();

    void buyProduct(String id);

    boolean checkProductIfExistByName(String name);
}
