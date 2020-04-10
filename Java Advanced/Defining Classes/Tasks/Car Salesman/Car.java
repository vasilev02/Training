package carSalesman;

public class Car {

    private String model;
    private String engine;
    private String weight;
    private String color;
    private Engine currentEngine;

    public Car(String model, String engine) {
        this.model = model;
        this.engine = engine;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCurrentEngine(Engine currentEngine) {
        this.currentEngine = currentEngine;
    }

    public String getModel() {
        return model;
    }

    public String getEngine() {
        return engine;
    }

    public String getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    public Engine getCurrentEngine() {
        return currentEngine;
    }
}
