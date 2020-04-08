package needForSpeed;

public class Vehicle {

    protected final static double DEFAULT_FUEL_CONSUMPTION = 1.25;

    protected double fuelConsumption;
    protected double fuel;
    protected int horsePower;

    public Vehicle(double fuel, int horsePower) {
        this.fuel = fuel;
        this.horsePower = horsePower;
        setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
    }

    public void drive(double kilometers){

        if (fuel>= kilometers*this.fuelConsumption) {
            this.fuel -=kilometers*this.fuelConsumption;
        }

    }



    public double getFuelConsumption() {
        return this.fuelConsumption;
    }

    public void setFuelConsumption(double entry) {
        this.fuelConsumption = entry;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }
}
