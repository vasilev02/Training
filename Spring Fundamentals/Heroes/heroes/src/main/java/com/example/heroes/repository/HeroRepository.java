package com.example.heroes.repository;

import com.example.heroes.model.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero, String> {

    @Query(value = "SELECT h FROM Hero AS h ORDER BY h.level DESC")
    List<Hero> getHeroes();

    Hero getHeroById(String id);

    Optional<Hero> getHeroByHeroName(String heroName);
}
