package com.example.demo.services.impl;

import com.example.demo.entities.Shampoo;
import com.example.demo.entities.Size;
import com.example.demo.repositories.ShampooRepository;
import com.example.demo.services.ShampooService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }


    @Override
    public Set<Shampoo> findAllByBrandName(String name) {

        return this.shampooRepository.findAllByBrand(name);

    }

    @Override
    public Set<Shampoo> findAllShampoosBySizeOrLabelId(String input, long labelId) {

        Size size = Size.valueOf(input);

        return this.shampooRepository.findAllBySizeOrLabel_IdOrderByPrice(size, labelId);

    }

    @Override
    public Set<Shampoo> findAllShampoosWithGivenSize(String name) {

        Size size = Size.valueOf(name);

        return this.shampooRepository.findAllBySizeEqualsOrderById(size);

    }

    @Override
    public Set<Shampoo> findAllShampoosHigherThenGivenPrice(String input) {

        BigDecimal price = new BigDecimal(input);

        return this.shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(price);

    }

    @Override
    public Set<Shampoo> findAllShampoosByGivenSetOfIngredients(Set<String> input) {

        return this.shampooRepository.selectShampoosByIngredients(input);

    }

    @Override
    public Set<Shampoo> findAllShampoosWithIngredientsLessThan(int input) {

        return this.shampooRepository.selectAllShampoosWithCountUnder(input);

    }
}
