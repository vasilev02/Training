package parkingSystem;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class SoftParkTest {

    private Car carOne;
    private Car carTwo;
    private SoftPark park;

    @Before
    public void implementFields(){
        this.carOne = new Car("Audi","CC 5555 CC");
        this.carTwo = new Car("BMW","BB 9999 BB");
        this.park = new SoftPark();
    }

    @Test
    public void checkSizeOfPark(){

        Assert.assertEquals(12,this.park.getParking().size());
    }

    @Test(expected = Exception.class)
    public void testGetParkingMethodShouldThrowExceptionClearCommand(){

        Map<String, Car> parking = this.park.getParking();
        parking.clear();
    }

    @Test(expected = Exception.class)
    public void testGetParkingMethodShouldThrowExceptionAddCommand(){

        Map<String, Car> parking = this.park.getParking();
        parking.put("A1",carOne);
    }

    @Test(expected = Exception.class)
    public void testGetParkingMethodShouldThrowExceptionRemoveCommand(){

        Map<String, Car> parking = this.park.getParking();
        parking.remove("A1",carOne);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParkCarMethodIllegalParkSpotShouldThrowException(){

        this.park.parkCar("InvalidPosition",carOne);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParkCarMethodAllReadyTakenSpotShouldThrowException(){

        this.park.parkCar("A1",carOne);
        this.park.parkCar("A1",carOne);
    }

    @Test(expected = IllegalStateException.class)
    public void testParkCarMethodAllReadyParkedShouldThrowException(){

        this.park.parkCar("A1",carOne);
        this.park.parkCar("A2",carOne);
    }

    @Test
    public void testParkCarMethodShouldWork(){

        String message = this.park.parkCar("A1", carOne);

        String expectedMess = "Car:CC 5555 CC parked successfully!";

        Assert.assertEquals(expectedMess,message);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCarMethodIfSpotDoesNotExistShouldThrowException(){

        this.park.parkCar("A1",carOne);
        this.park.removeCar("InvalidSpot",carOne);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCarMethodIfSpotDoesNotHaveACarShouldThrowException(){

        this.park.parkCar("A1",carOne);
        this.park.removeCar("InvalidSpot",null);
    }

    @Test
    public void testRemoveCarMethodShouldWork(){

        this.park.parkCar("A1",carOne);
        String message = this.park.removeCar("A1", carOne);

        String expectedMessage = "Remove car:CC 5555 CC successfully!";

        Assert.assertEquals(expectedMessage,message);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCarMethodShouldNotWorkWork2(){

        this.park.parkCar("A1",carOne);

        this.park.removeCar("A1",carTwo);

    }

    @Test
    public void testRemoveCarWork2(){

        this.park.parkCar("A1",carOne);

        this.park.removeCar("A1",carOne);

        Assert.assertEquals(this.park.getParking().get("A1"),null);

    }

}
