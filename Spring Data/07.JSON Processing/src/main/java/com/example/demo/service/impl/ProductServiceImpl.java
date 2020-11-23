package com.example.demo.service.impl;

import com.example.demo.domain.dto.ProductExportDto;
import com.example.demo.domain.dto.ProductSeedDto;
import com.example.demo.domain.entities.Category;
import com.example.demo.domain.entities.Product;
import com.example.demo.domain.entities.User;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public void seedProductsInDB(ProductSeedDto[] productSeedDtos) {

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        for (ProductSeedDto current : productSeedDtos) {

            Set<ConstraintViolation<ProductSeedDto>> violations = validator.validate(current);

            if (violations.size() > 0) {
                for (ConstraintViolation<ProductSeedDto> violation : violations) {
                    System.out.println(violation);
                }
            } else {

                Product product = modelMapper.map(current, Product.class);

                product.setSeller(this.setRandomSeller());

                product.setBuyer(this.setRandomBuyer());

                product.setCategories(this.setRandomCategories());

                this.productRepository.saveAndFlush(product);

            }

        }


    }

    @Transactional
    @Override
    public List<ProductExportDto> getAllProductsBetweenTwoPrices(Double min, Double max) {

        BigDecimal minDecimal = BigDecimal.valueOf(min);
        BigDecimal maxDecimal = BigDecimal.valueOf(max);

        List<ProductExportDto> dtos = new LinkedList<>();


        List<Product> products = this.productRepository.findAllByPriceBetweenOrderByPrice(minDecimal, maxDecimal);

        for (Product product : products) {

            String fullName = product.getSeller().getFirstName() + " " +
                    product.getSeller().getLastName();

            if(product.getSeller().getFirstName() == null){
                fullName = product.getSeller().getLastName();
            }

            ProductExportDto exportDto = this.modelMapper.map(product, ProductExportDto.class);

            exportDto.setSeller(fullName);

            dtos.add(exportDto);

        }

        return dtos;

    }

    private Set<Category> setRandomCategories() {

        Set<Category> categories = new HashSet<>();

        Random random = new Random();

        if (this.categoryRepository.count() == 0) {
            return new HashSet<>();
        }

        int id = random.nextInt((int) this.categoryRepository.count() + 1) + 1;

        categories.add(this.categoryRepository.findById(id));

        return categories;

    }

    private User setRandomBuyer() {

        Random random = new Random();

        int id = random.nextInt((int) this.userRepository.count() - 1) + 1;

        if (id % 4 == 0) {
            return null;
        }

        return this.userRepository.findById(id);

    }

    private User setRandomSeller() {

        Random random = new Random();

        int id = random.nextInt((int) this.userRepository.count() - 1) + 1;

        return this.userRepository.findById(id);

    }
}
