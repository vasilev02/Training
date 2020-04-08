package aquarium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AquariumTests {

    private Fish fishOne;
    private Fish fishTwo;

    @Before
    public void implementField(){
        this.fishOne = new Fish("Ivo");
        this.fishTwo = new Fish("Gosho");
    }

    @Test
    public void makeAquariumAndCheckName(){
        Aquarium aquarium = new Aquarium("Name",5);
        Assert.assertEquals(aquarium.getName(),"Name");
    }

    @Test
    public void makeAquariumAndCheckCapacity(){
        Aquarium aquarium = new Aquarium("Name",5);
        Assert.assertEquals(aquarium.getCapacity(),5);
    }

    @Test
    public void makeAquariumAndCheckSize(){
        Aquarium aquarium = new Aquarium("Name",5);
        aquarium.add(fishOne);
        Assert.assertEquals(1,aquarium.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void checkSetNameWithNull(){
        Aquarium aquarium = new Aquarium(null,5);
    }

    @Test(expected = NullPointerException.class)
    public void checkSetNameWithSpaces(){
        Aquarium aquarium = new Aquarium("     ",5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkSetCapacityWithNumberUnderZero(){
        Aquarium aquarium = new Aquarium("Name",-1);
    }

    @Test
    public void checkGetCapacityIFWorkCorrectly(){
        Aquarium aquarium = new Aquarium("Name",5);
        int capacity = aquarium.getCapacity();
        Assert.assertEquals(5,capacity);
    }

    @Test
    public void testGetCountMethod(){
        Aquarium aquarium = new Aquarium("Name",5);
        aquarium.add(fishOne);
        aquarium.add(fishTwo);
        Assert.assertEquals(aquarium.getCount(),2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkAddingMethodShouldThrowException(){
        Aquarium aquarium = new Aquarium("Name",1);
        aquarium.add(fishOne);
        aquarium.add(fishTwo);
    }

    @Test
    public void checkAddingMethodShouldWork(){
        Aquarium aquarium = new Aquarium("Name",2);
        aquarium.add(fishOne);
        Assert.assertEquals(aquarium.getCount(),1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkRemoveMethodShouldThrowException(){
        Aquarium aquarium = new Aquarium("Name",2);
        aquarium.add(fishOne);
        aquarium.add(fishTwo);
        aquarium.remove("Marti");
    }

    @Test
    public void checkRemoveMethodShouldWork(){
        Aquarium aquarium = new Aquarium("Name",2);
        aquarium.add(fishOne);
        aquarium.add(fishTwo);
        aquarium.remove("Ivo");
        int count = aquarium.getCount();
        Assert.assertEquals(count,1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkSellFishMethodShouldThrowException(){
        Aquarium aquarium = new Aquarium("Name",2);
        aquarium.add(fishOne);
        aquarium.add(fishTwo);
        Fish fish = aquarium.sellFish("Nobobdy!!!");
    }

    @Test
    public void checkSellFishMethodShouldSetFishIFitIsAvailable(){
        Aquarium aquarium = new Aquarium("Name",2);
        aquarium.add(fishOne);
        aquarium.add(fishTwo);
        Fish fish = aquarium.sellFish("Ivo");
        Assert.assertFalse(fish.isAvailable());
    }

    @Test
    public void checkSellFishMethodShouldWork(){
        Aquarium aquarium = new Aquarium("Name",2);
        aquarium.add(fishOne);
        aquarium.add(fishTwo);
        Fish fish = aquarium.sellFish("Ivo");
        Assert.assertEquals(fish.getName(),"Ivo");
    }

    @Test
    public void checkReportMethodIfWorkCorrectly(){
        Aquarium aquarium = new Aquarium("Name",2);
        aquarium.add(fishOne);
        aquarium.add(fishTwo);

        String report = aquarium.report();

        String message = "Fish available at Name: Ivo, Gosho";

        Assert.assertEquals(report,message);

    }

}

