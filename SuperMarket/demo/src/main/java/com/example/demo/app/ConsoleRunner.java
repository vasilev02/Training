package com.example.demo.app;

import com.example.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private CategoryService categoryService;
    private TownService townService;
    private ShopService shopService;
    private SellerService sellerService;
    private ProductService productService;

    @Autowired
    public ConsoleRunner(CategoryService categoryService, TownService townService, ShopService shopService, SellerService sellerService, ProductService productService) {
        this.categoryService = categoryService;
        this.townService = townService;
        this.shopService = shopService;
        this.sellerService = sellerService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Hi");
        System.out.println("Choose option from:");
        System.out.println("1 - for Add Category");
        System.out.println("2 - for Add Town");
        System.out.println("3 - for Add Shop");
        System.out.println("4 - for Add Seller");
        System.out.println("5 - for Add Product");
        System.out.println("6 - for Set seller new manager");
        System.out.println("7 - for Distributing product in shop");
        System.out.println("8 - for Showing all sellers in Shop");
        System.out.println("9 - for Showing all products in Shop");
        System.out.println("0 - for Stop");

        Scanner scanner = new Scanner(System.in);

        String index = scanner.nextLine();

        while (!index.equals("0")) {
            switch (index) {

                case "1":
                    System.out.println("Enter category name:");
                    String categoryName = scanner.nextLine();
                    System.out.println(categoryService.addCategory(categoryName));
                    break;

                case "2":
                    System.out.println("Enter town name:");
                    String townName = scanner.nextLine();
                    System.out.println(townService.addTown(townName));
                    break;

                case "3":
                    System.out.println("Enter shop details in format: name address town");
                    String shopDetails = scanner.nextLine();
                    System.out.println(shopService.addShop(shopDetails));
                    break;

                case "4":
                    System.out.println("Enter seller details in format: firstName lastName age salary shopName");
                    String sellerDetails = scanner.nextLine();
                    System.out.println(sellerService.addSeller(sellerDetails));
                    break;

                case "5":
                    System.out.println("Enter product details in format: name price bestBefore(dd-MM-yyyy) category");
                    String productDetails = scanner.nextLine();
                    System.out.println(productService.addProduct(productDetails));
                    break;

                case "6":
                    System.out.println("Enter seller first and last names:");
                    String sellerNames = scanner.nextLine();

                    System.out.println("Enter manager first and last names:");
                    String managerNames = scanner.nextLine();
                    System.out.println(sellerService.addManagerToSeller(sellerNames, managerNames));
                    break;

                case "7":
                    System.out.println("Enter product name:");
                    String productName = scanner.nextLine();

                    System.out.println("Enter product distribution in Shop names in format [shopName1 shopName2 ...]");
                    String shopsNames = scanner.nextLine();
                    System.out.println(productService.distributeProduct(productName, shopsNames));
                    break;

                case "8":
                    System.out.println("Enter shop name:");
                    String shopNameForSellers = scanner.nextLine();

                    System.out.println(shopService.showAllSellers(shopNameForSellers));
                    break;

                case "9":
                    System.out.println("Enter shop name:");
                    String shopNameForProducts = scanner.nextLine();

                    System.out.println(shopService.showAllProducts(shopNameForProducts));
                    break;

            }
            index = scanner.nextLine();
        }
        System.out.println("- - End program - -");
    }

}
