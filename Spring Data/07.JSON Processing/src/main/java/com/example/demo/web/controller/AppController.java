package com.example.demo.web.controller;

import com.example.demo.domain.dto.*;
import com.example.demo.domain.entities.Category;
import com.example.demo.domain.entities.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import com.example.demo.util.FileUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AppController implements CommandLineRunner {

    private final static String USERS_PATH_FILE = "src/main/resources/jsonData/users.json";
    private final static String CATEGORIES_PATH_FILE = "src/main/resources/jsonData/categories.json";
    private final static String PRODUCTS_PATH_FILE = "src/main/resources/jsonData/products.json";

    private final FileUtil fileUtil;
    private final Gson gson;

    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public AppController(FileUtil fileUtil, Gson gson, UserService userService, ProductService productService, CategoryService categoryService) {
        this.fileUtil = fileUtil;
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.gson = new GsonBuilder().
                excludeFieldsWithoutExposeAnnotation().
                setPrettyPrinting().
                create();
    }

    @Override
    public void run(String... args) throws IOException {

        //this.seedUsers();
        //this.seedCategories();
        //this.seedProducts();

        this.productsInRange();

        //this.userSellingInfo();

        //this.categoriesInfo();

        //this.getAllInfo();

    }

    private void getAllInfo() {

        UsersInfoDto info = this.userService.getUsersWithAtLeastOneBuyer();

        System.out.println(gson.toJson(info));

    }

    private void categoriesInfo() {

        Set<CategoryDto> categories = this.categoryService.getCategoriesInfo();

        System.out.println(gson.toJson(categories));

    }

    private void userSellingInfo() {

        Set<UserDto> users = this.userService.getUsersInfo();

        System.out.println(gson.toJson(users));

    }

    private void productsInRange() {

        List<ProductExportDto> products = this.productService.getAllProductsBetweenTwoPrices(500.0, 1000.0);

        System.out.println(gson.toJson(products));

    }

    private void seedCategories() throws IOException {

        String fileResult = fileUtil.readFileContent(CATEGORIES_PATH_FILE);

        CategorySeedDto[] categorySeedDtos = gson.fromJson(fileResult, CategorySeedDto[].class);

        this.categoryService.seedCategoriesInDB(categorySeedDtos);

    }

    private void seedProducts() throws IOException {

        String fileResult = fileUtil.readFileContent(PRODUCTS_PATH_FILE);

        ProductSeedDto[] productSeedDtos = gson.fromJson(fileResult, ProductSeedDto[].class);

        this.productService.seedProductsInDB(productSeedDtos);

    }

    private void seedUsers() throws IOException {

        String fileResult = fileUtil.readFileContent(USERS_PATH_FILE);

        UserSeedDto[] userSeedDtos = gson.fromJson(fileResult, UserSeedDto[].class);

        this.userService.seedUsersInDB(userSeedDtos);

    }


}
