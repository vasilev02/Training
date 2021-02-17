package com.example.heroes.service.impl;

import com.example.heroes.model.view.HeroDetailsViewModel;
import com.example.heroes.model.view.HeroViewModel;
import com.example.heroes.model.entity.Hero;
import com.example.heroes.model.service.HeroAddServiceModel;
import com.example.heroes.repository.HeroRepository;
import com.example.heroes.service.HeroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeroServiceImpl implements HeroService {

    private final HeroRepository heroRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HeroServiceImpl(HeroRepository heroRepository, ModelMapper modelMapper) {
        this.heroRepository = heroRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public HeroAddServiceModel addHero(HeroAddServiceModel serviceModel) {

        Hero hero = this.modelMapper.map(serviceModel, Hero.class);
        this.heroRepository.saveAndFlush(hero);
        return serviceModel;
    }

    @Override
    public List<HeroViewModel> getHeroes() {
        return this.heroRepository.getHeroes().stream().map(hero -> {
            return this.modelMapper.map(hero, HeroViewModel.class);
        }).collect(Collectors.toList());
    }

    @Override
    public HeroDetailsViewModel getHeroById(String id) {
        Hero hero = this.heroRepository.getHeroById(id);
        return this.modelMapper.map(hero, HeroDetailsViewModel.class);
    }

    @Override
    public void deleteHeroById(String id) {
        this.heroRepository.deleteById(id);
    }

    @Override
    public boolean checkHeroName(String heroName) {
        return this.heroRepository.getHeroByHeroName(heroName).isPresent();
    }
}
