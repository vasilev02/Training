package com.example.demo.controllers;

import com.example.demo.entities.Ingredient;
import com.example.demo.entities.Shampoo;
import com.example.demo.services.IngredientService;
import com.example.demo.services.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@Controller
public class AppController implements CommandLineRunner {

    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    public AppController(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        //this.executeTaskZero(input);

        //this.executeTaskOne(input);

        //this.executeTaskTwo(input);

        //this.executeTaskThree(input);

        //this.executeTaskFour(input);

        //Set<String> names = new HashSet<>();

        //names.add("Lavender");
        //names.add("Herbs");
        //names.add("Apple");

        //this.executeTaskFive(names);

        //this.executeTaskSix(input);

        //Set<String> namesOfIngredients = new HashSet<>();

        //namesOfIngredients.add("Berry");
        //namesOfIngredients.add("Mineral-Collagen");

        //this.executeTaskSeven(namesOfIngredients);

        //this.executeTaskEight(input);

        //this.executeTaskNine(input);

        //this.executeTaskTen(input);

        //Set<String> namesOfIngredients = new HashSet<>();

        //namesOfIngredients.add("Berry");
        //namesOfIngredients.add("Mineral-Collagen");

        //this.executeTaskEleven(namesOfIngredients);


    }

    private void executeTaskTwo(String input) {

        Scanner scanner = new Scanner(System.in);

        long id = Integer.parseInt(scanner.nextLine());

        Set<Shampoo> result = this.shampooService.findAllShampoosBySizeOrLabelId(input, id);

        result.forEach(entry -> {

            System.out.printf("%s %s %.2flv.%n",entry.getBrand(),entry.getSize() , entry.getPrice());

        });

    }

    private void executeTaskEleven(Set<String> namesOfIngredients) {

        this.ingredientService.updateIngredientPriceByGivenSetOfIngredients(namesOfIngredients);

    }

    private void executeTaskTen(String name) {

        this.ingredientService.updateIngredientPriceByGivenName(name);

    }

    private void executeTaskNine(String name) {

        this.ingredientService.deleteIngredientByGivenName(name);

    }

    private void executeTaskEight(String input) {

        int count = Integer.parseInt(input);

        Set<Shampoo> result = this.shampooService.findAllShampoosWithIngredientsLessThan(count);

        result.forEach(entry -> {

            System.out.printf("%s%n",entry.getBrand());

        });

    }

    private void executeTaskSeven(Set<String> namesOfIngredients) {

        Set<Shampoo> result = this.shampooService.findAllShampoosByGivenSetOfIngredients(namesOfIngredients);

        result.forEach(entry -> {

            System.out.printf("%s%n",entry.getBrand());

        });

    }

    private void executeTaskSix(String input) {

     BigDecimal decimal = new BigDecimal(input);

        Set<Ingredient> result = this.ingredientService.findIngredientsCountByPrice(decimal);

        System.out.println(result.size());

    }

    private void executeTaskFive(Set<String> names) {

        Set<Ingredient> result = this.ingredientService.findIngredientsContainingInList(names);

        result.forEach(entry -> {

            System.out.printf("%s%n",entry.getName());

        });

    }


    private void executeTaskFour(String input) {

        Set<Ingredient> result = this.ingredientService.findIngredientsByName(input);

        result.forEach(entry -> {

            System.out.printf("%s%n",entry.getName());

        });

    }

    private void executeTaskThree(String priceInput) {

        Set<Shampoo> result = this.shampooService.findAllShampoosHigherThenGivenPrice(priceInput);

        result.forEach(entry -> {

            System.out.printf("%s %s %.2flv.%n",entry.getBrand(),entry.getSize() , entry.getPrice());

        });

    }

    private void executeTaskOne(String inputSizeName) {

        Set<Shampoo> result = this.shampooService.findAllShampoosWithGivenSize(inputSizeName);

        result.forEach(entry -> {

            System.out.printf("%s %s %.2flv.%n",entry.getBrand(),entry.getSize() , entry.getPrice());

        });

    }

    private void executeTaskZero(String inputBrand) {

        Set<Shampoo> result = this.shampooService.findAllByBrandName(inputBrand);

        result.forEach(entry -> {

            System.out.printf("%s %.2f%n",entry.getBrand(), entry.getPrice());

        });

    }
}
