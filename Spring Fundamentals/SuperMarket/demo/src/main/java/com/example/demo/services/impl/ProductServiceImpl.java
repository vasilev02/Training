package com.example.demo.services.impl;

import com.example.demo.models.entities.Category;
import com.example.demo.models.entities.Product;
import com.example.demo.models.entities.Shop;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.ShopRepository;
import com.example.demo.services.ProductService;
import com.example.demo.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ShopRepository shopRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ShopRepository shopRepository, ValidationUtil validationUtil) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.shopRepository = shopRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public String addProduct(String data) {
        StringBuilder sb = new StringBuilder();
        String[] productInfo = data.split("\\s+");

        if (productInfo.length != 4){
            return sb.append("Empty fields! - Try again!").append(System.lineSeparator())
                    .toString().trim();
        }

        Product findProduct = this.productRepository.findByName(productInfo[0]);
        Category findCategory = this.categoryRepository.findByName(productInfo[3]);

        if (findCategory == null) {
            return sb.append("Category does not exist!").append(System.lineSeparator())
                    .toString().trim();
        }

        if (findProduct == null) {

            Product product = new Product();
            product.setName(productInfo[0]);

            BigDecimal price = new BigDecimal(productInfo[1]);
            product.setPrice(price);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(productInfo[2],formatter);
            product.setBestBefore(date);

            product.setCategory(findCategory);

            product.setDescription("Some Description");

            if (validationUtil.isValid(product)) {
                this.productRepository.saveAndFlush(product);
                sb.append("Successfully added product!").append(System.lineSeparator());
                sb.append("============================").append(System.lineSeparator());
            } else {
                sb.append("Invalid input data!").append(System.lineSeparator());
            }

        } else {
            sb.append(String.format("Product with name %s already exists!",productInfo[0])).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public String distributeProduct(String productName, String shopsNames) {

        StringBuilder sb = new StringBuilder();

        Product findProduct = this.productRepository.findByName(productName);

        if(findProduct == null){
            return sb.append("Product does not exist!").append(System.lineSeparator())
                    .toString().trim();
        }

        String[] shops = shopsNames.split("\\s+");

        for (String shopName : shops) {

            Shop current = this.shopRepository.findByName(shopName);

            if(current != null){

                findProduct.getShops().add(current);

                sb.append(String.format("Successfully distributed product (%s) in shop (%s)!",productName,shopName));

            }else{
                sb.append(String.format("Shop (%s) does not exist!",shopName));
            }
            sb.append(System.lineSeparator());
        }

        this.productRepository.saveAndFlush(findProduct);

        return sb.toString().trim();
    }
}
