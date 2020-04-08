package unitTesting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class RaceEntryTest {

    private UnitMotorcycle motor;
    private UnitRider rider;
    private UnitRider rider2;


    @Before
    public void setValuesToProperties(){

        motor = new UnitMotorcycle("Honda",100,200);
        rider = new UnitRider("Joro",motor);
        rider2 = new UnitRider("Mitko",motor);

    }

    @Test(expected = NullPointerException.class)
    public void checkIfRiderIsNull(){

        RaceEntry entry = new RaceEntry();
        entry.addRider(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfRiderIsAlreadyExist(){

        RaceEntry entry = new RaceEntry();
        entry.addRider(rider);
        entry.addRider(rider);
    }

    @Test
    public void checkAddFunctionIsCorrect(){

        RaceEntry entry = new RaceEntry();
        String message = entry.addRider(rider);

        String expectedMessage = "Rider Joro added in race.";

        Assert.assertEquals(expectedMessage,message);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateAverageHorsePowerWithUnderTwoPeople(){

        RaceEntry entry = new RaceEntry();

        entry.addRider(rider);

        entry.calculateAverageHorsePower();
    }

    @Test
    public void calculateAverageHorsePowerWithTwoAndMorePeople(){

        RaceEntry entry = new RaceEntry();

        entry.addRider(rider);
        entry.addRider(rider2);

        double result = entry.calculateAverageHorsePower();

        Assert.assertEquals(100,result,0.001);
    }


    @Test(expected = Exception.class)
    public void checkIfCollectionThrowException1(){

        RaceEntry entry = new RaceEntry();
        entry.addRider(rider);
        Collection<UnitRider> riders = entry.getRiders();

        riders.clear();

    }
    
}
