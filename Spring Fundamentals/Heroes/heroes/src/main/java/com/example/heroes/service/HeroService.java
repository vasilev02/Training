package com.example.heroes.service;

import com.example.heroes.model.view.HeroDetailsViewModel;
import com.example.heroes.model.view.HeroViewModel;
import com.example.heroes.model.service.HeroAddServiceModel;

import java.util.List;

public interface HeroService {
    HeroAddServiceModel addHero(HeroAddServiceModel serviceModel);

    List<HeroViewModel> getHeroes();

    HeroDetailsViewModel getHeroById(String id);

    void deleteHeroById(String id);

    boolean checkHeroName(String heroName);
}
