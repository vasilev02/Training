package com.example.demo.services.impl;

import com.example.demo.entities.Ingredient;
import com.example.demo.repositories.IngredientRepository;
import com.example.demo.services.IngredientService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Set<Ingredient> findIngredientsByName(String input) {

        return this.ingredientRepository.findAllByNameEquals(input);

    }

    @Override
    public Set<Ingredient> findIngredientsContainingInList(Set<String> input) {

        return this.ingredientRepository.findAllByNameIn(input);

    }

    @Override
    public Set<Ingredient> findIngredientsCountByPrice(BigDecimal price) {

        return this.ingredientRepository.findCountByPriceLessThan(price);

    }

    @Override
    public void deleteIngredientByGivenName(String input) {

        this.ingredientRepository.deleteIngredientByName(input);

    }

    @Override
    public void updateIngredientPriceByGivenName(String input) {

        this.ingredientRepository.updateIngredientPriceBy(input);

    }

    @Override
    public void updateIngredientPriceByGivenSetOfIngredients(Set<String> input) {

        this.ingredientRepository.updateIngredientPriceByGivenSet(input);

    }
}
