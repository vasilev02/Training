package com.example.demo.service.impl;

import com.example.demo.domain.dto.CategoryDto;
import com.example.demo.domain.dto.CategorySeedDto;
import com.example.demo.domain.dto.UserSeedDto;
import com.example.demo.domain.entities.Category;
import com.example.demo.domain.entities.Product;
import com.example.demo.domain.entities.User;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategoriesInDB(CategorySeedDto[] categorySeedDtos) {

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();


        for (CategorySeedDto current : categorySeedDtos) {

            Set<ConstraintViolation<CategorySeedDto>> violations = validator.validate(current);

            if (violations.size() > 0) {
                for (ConstraintViolation<CategorySeedDto> violation : violations) {
                    System.out.println(violation);
                }
            } else {

                Category category = modelMapper.map(current, Category.class);

                this.categoryRepository.saveAndFlush(category);

            }

        }

    }

    @Override
    public Set<CategoryDto> getCategoriesInfo() {

        Set<Category> categories = this.categoryRepository.getAllCategories();

        Set<CategoryDto> dtos = new HashSet<>();

        for (Category current : categories) {

            CategoryDto categoryDto = this.modelMapper.map(current,CategoryDto.class);

            categoryDto.setProductsCount(current.getProducts().size());

            categoryDto.setAveragePrice(getAveragePrice(current));

            categoryDto.setTotalRevenue(getRevenuePrice(current));

            dtos.add(categoryDto);

        }

        return dtos;
    }

    private BigDecimal getAveragePrice(Category category) {

        BigDecimal decimal = new BigDecimal(0);
        BigDecimal size = new BigDecimal(category.getProducts().size());

        for (Product current : category.getProducts()) {

            decimal = decimal.add(current.getPrice());

        }

        decimal = decimal.divide(size,3, RoundingMode.DOWN);
        return decimal;
    }

    private BigDecimal getRevenuePrice(Category category) {

        BigDecimal decimal = new BigDecimal(0);

        for (Product current : category.getProducts()) {

            decimal = decimal.add(current.getPrice());

        }

        return decimal;
    }


}
