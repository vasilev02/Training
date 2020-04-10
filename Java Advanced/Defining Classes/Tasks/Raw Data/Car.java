package rawData;

public class Car {

    private String name;
    private int enginePower;
    private int engineWeight;
    private String cargoType;
    private double average;

    public Car(String name, int enginePower, int engineWeight, String cargoType, double average) {
        this.name = name;
        this.enginePower = enginePower;
        this.engineWeight = engineWeight;
        this.cargoType = cargoType;
        this.average = average;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public double getAverage() {
        return average;
    }

}
