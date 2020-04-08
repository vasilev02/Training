package needForSpeed;

public class RaceMotorcycle extends Motorcycle {

    protected final static double DEFAULT_FUEL_CONSUMPTION = 8;

    public RaceMotorcycle(double fuel, int horsePower) {
        super(fuel, horsePower);
        setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
    }

    public void drive(double kilometers){

        if (fuel>= kilometers*this.fuelConsumption) {
            this.fuel -=kilometers*this.fuelConsumption;
        }

    }


}
