package com.example.demo.services;

import com.example.demo.entities.Ingredient;

import java.math.BigDecimal;
import java.util.Set;

public interface IngredientService {

    Set<Ingredient> findIngredientsByName(String input);

    Set<Ingredient> findIngredientsContainingInList(Set<String> input);

    Set<Ingredient> findIngredientsCountByPrice(BigDecimal input);

    void deleteIngredientByGivenName(String input);

    void updateIngredientPriceByGivenName(String input);

    void updateIngredientPriceByGivenSetOfIngredients(Set<String> input);

}
