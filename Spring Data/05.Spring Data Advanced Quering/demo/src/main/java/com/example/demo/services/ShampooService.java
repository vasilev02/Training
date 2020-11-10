package com.example.demo.services;


import com.example.demo.entities.Shampoo;

import java.util.Set;

public interface ShampooService {

    Set<Shampoo> findAllByBrandName(String input);
    Set<Shampoo> findAllShampoosBySizeOrLabelId(String input, long labelInput);
    Set<Shampoo> findAllShampoosWithGivenSize(String input);
    Set<Shampoo> findAllShampoosHigherThenGivenPrice(String input);
    Set<Shampoo> findAllShampoosByGivenSetOfIngredients(Set<String> input);
    Set<Shampoo> findAllShampoosWithIngredientsLessThan(int input);

}
