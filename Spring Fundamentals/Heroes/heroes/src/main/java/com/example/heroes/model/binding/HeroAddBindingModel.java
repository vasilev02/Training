package com.example.heroes.model.binding;

import com.example.heroes.model.enumeration.HeroClassEnum;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

public class HeroAddBindingModel {

    private String heroName;
    private HeroClassEnum heroClass;
    private int level;

    public HeroAddBindingModel() {
    }

    @NotBlank(message = "Type hero name")
    @Size(min = 2, max = 20, message = "Hero name must be between 2 and 20 characters inclusive")
    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    @NotNull(message = "Choose hero class")
    public HeroClassEnum getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(HeroClassEnum classEnum) {
        this.heroClass = classEnum;
    }

    @NotNull(message = "Type hero level")
    @Min(value = 1)
    @Max(value = 100)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
