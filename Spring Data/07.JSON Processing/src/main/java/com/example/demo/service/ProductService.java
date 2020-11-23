package com.example.demo.service;

import com.example.demo.domain.dto.ProductExportDto;
import com.example.demo.domain.dto.ProductSeedDto;
import com.example.demo.domain.entities.Product;

import java.util.List;


public interface ProductService {

    void seedProductsInDB(ProductSeedDto[] productSeedDtos);

    List<ProductExportDto> getAllProductsBetweenTwoPrices(Double min, Double max);

}
