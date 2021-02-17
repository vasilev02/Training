package com.example.heroes.model.entity;

import com.example.heroes.model.enumeration.HeroClassEnum;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "heroes")
public class Hero extends BaseEntity {

    private String heroName;
    private HeroClassEnum heroClass;
    private int level;

    public Hero() {
    }

    @Size(min = 2, max = 20, message = "Hero name must be between 2 and 20 characters inclusive")
    @Column(name = "name", nullable = false, unique = true)
    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "class", nullable = false)
    public HeroClassEnum getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(HeroClassEnum classEnum) {
        this.heroClass = classEnum;
    }

    @Min(value = 1)
    @Max(value = 100)
    @Column(name = "level", nullable = false)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
