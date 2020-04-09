package vehicles;

public abstract class Vehicle {

    private double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;

    public double getTankCapacity() {
        return this.tankCapacity;
    }

    protected void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public double getFuelQuantity() {
        return this.fuelQuantity;
    }

    public double getFuelConsumption() {
        return this.fuelConsumption;
    }

    protected void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    protected void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public Vehicle(double fuelQuantity, double fuelConsumption,double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
    }

    public abstract void drive(double distance);

    public abstract void refuel(double liters);

}
