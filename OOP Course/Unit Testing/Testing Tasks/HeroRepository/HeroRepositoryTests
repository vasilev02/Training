package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HeroRepositoryTests {

    private Hero heroOne;
    private Hero heroTwo;

    private HeroRepository repository;

    @Before
    public void implementFields(){
        heroOne = new Hero("Ivo",5);
        heroTwo = new Hero("Pesho",10);
        repository = new HeroRepository();
    }

    @Test(expected = NullPointerException.class)
    public void createMethodShouldThrowExceptionWithNull(){

        repository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createMethodShouldThrowExceptionWithTrimmer(){

        repository.create(heroOne);
        repository.create(heroOne);
    }

    @Test
    public void createMethodShouldWorkWithSize(){

        repository.create(heroOne);
        repository.create(heroTwo);
        int size = repository.getCount();
        Assert.assertEquals(2,size);
    }

    @Test
    public void createMethodShouldWorkWithMessage(){

        String message = repository.create(heroOne);

        String expectedMessage = "Successfully added hero Ivo with level 5";
        Assert.assertEquals(expectedMessage,message);
    }

    @Test(expected = NullPointerException.class)
    public void removeMethodShouldThrowExceptionWithNull(){

        repository.remove(null);
    }

    @Test(expected = NullPointerException.class)
    public void removeMethodShouldThrowExceptionWithTrimmer(){

        repository.remove("      ");
    }

    @Test
    public void removeMethodShouldWorkWithBoolean(){

        this.repository.create(heroOne);
        this.repository.create(heroTwo);

        boolean bool = repository.remove("Ivo");

        Assert.assertTrue(bool);
    }

    @Test
    public void removeMethodShouldWorkWithSize(){

        this.repository.create(heroOne);
        this.repository.create(heroTwo);

        boolean bool = repository.remove("Ivo");

        Assert.assertEquals(1,this.repository.getCount());
    }

    @Test
    public void getHighestLevelShouldWork(){

        this.repository.create(heroOne);
        this.repository.create(heroTwo);

        Hero hero = this.repository.getHeroWithHighestLevel();

        Assert.assertEquals("Pesho",hero.getName());
    }

    @Test
    public void getHighestLevelShouldWorkWithNull(){

        Hero hero = this.repository.getHeroWithHighestLevel();

        Assert.assertEquals(null,hero);
    }

    @Test
    public void getHeroShouldWork(){

        this.repository.create(heroOne);
        this.repository.create(heroTwo);

        Hero hero = this.repository.getHero("Pesho");

        Assert.assertEquals("Pesho",hero.getName());
    }

    @Test
    public void getHeroShouldWorkWithNull(){

        this.repository.create(heroOne);
        this.repository.create(heroTwo);

        Hero hero = this.repository.getHero("Nobody!!!");


        Assert.assertEquals(null,hero);
    }

    @Test(expected = Exception.class)
    public void getHeroesMethodShouldThrowExceptionClear(){

        this.repository.create(heroOne);
        this.repository.create(heroTwo);

        Collection<Hero> heroes = this.repository.getHeroes();
        heroes.clear();
    }

    @Test(expected = Exception.class)
    public void getHeroesMethodShouldThrowExceptionAdd(){

        this.repository.create(heroOne);
        this.repository.create(heroTwo);

        Collection<Hero> heroes = this.repository.getHeroes();
        heroes.add(new Hero("Nobody!!!",5000));
    }

}
