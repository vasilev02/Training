package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {

    private Astronaut astronautOne;
    private Astronaut astronautTwo;
    private Spaceship spaceship;

    @Before
    public void implementFields(){
        this.astronautOne = new Astronaut("Ivo",2.5);
        this.astronautTwo = new Astronaut("Mitko",5.5);
    }

    @Test(expected = NullPointerException.class)
    public void createSpaceShipWithNameEqualsNullShouldThrowException(){

        this.spaceship = new Spaceship(null,5);
    }

    @Test(expected = NullPointerException.class)
    public void createSpaceShipWithEmptyNameShouldThrowException(){

        this.spaceship = new Spaceship("    ",5);
    }

    @Test
    public void createSpaceShipWithNameShouldWork(){

        this.spaceship = new Spaceship("Maven",5);
        Assert.assertEquals("Maven",this.spaceship.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createSpaceShipWithIllegalCapacityShouldThrowException(){

        this.spaceship = new Spaceship("Maven",-1);
    }

    @Test
    public void createSpaceShipWithCapacityShouldWork(){

        this.spaceship = new Spaceship("Maven",5);
        Assert.assertEquals(5,this.spaceship.getCapacity());
    }

    @Test
    public void testGetCountMethodShouldWork(){

        this.spaceship = new Spaceship("Maven",5);
        this.spaceship.add(astronautOne);
        this.spaceship.add(astronautTwo);
        Assert.assertEquals(2,this.spaceship.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMethodShouldThrowExceptionCapacity(){

        this.spaceship = new Spaceship("Maven",1);
        this.spaceship.add(astronautOne);
        this.spaceship.add(astronautTwo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMethodShouldThrowExceptionTwoEqualsPerson(){

        this.spaceship = new Spaceship("Maven",2);
        this.spaceship.add(astronautOne);
        this.spaceship.add(astronautOne);
    }

    @Test
    public void testAddMethodShouldWork(){

        this.spaceship = new Spaceship("Maven",5);
        this.spaceship.add(astronautOne);
        this.spaceship.add(astronautTwo);
        Assert.assertEquals(2,this.spaceship.getCount());
    }

    @Test
    public void testRemoveMethodShouldNotWorkWithInvalidName(){

        this.spaceship = new Spaceship("Maven",2);
        this.spaceship.add(astronautOne);
        boolean result = this.spaceship.remove("Nobody!!!");

        Assert.assertFalse(result);
    }

    @Test
    public void testRemoveMethodShouldWorkWithValidName(){

        this.spaceship = new Spaceship("Maven",2);
        this.spaceship.add(astronautOne);
        this.spaceship.add(astronautTwo);
        boolean result = this.spaceship.remove("Ivo");

        Assert.assertTrue(result);
    }

    @Test
    public void testRemoveMethodShouldWorkWithSize(){

        this.spaceship = new Spaceship("Maven",2);
        this.spaceship.add(astronautOne);
        this.spaceship.add(astronautTwo);
        boolean result = this.spaceship.remove("Ivo");

        Assert.assertEquals(1,this.spaceship.getCount());
    }

}
