package needForSpeed;

public class Car extends Vehicle {

    protected final static double DEFAULT_FUEL_CONSUMPTION = 3;

    public Car(double fuel, int horsePower) {
        super(fuel, horsePower);
        setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
    }

    public void drive(double kilometers){

        if (fuel>= kilometers*this.fuelConsumption) {
            this.fuel -=kilometers*this.fuelConsumption;
        }

    }

}
