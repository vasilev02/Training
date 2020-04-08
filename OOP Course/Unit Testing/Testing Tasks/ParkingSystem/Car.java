package parkingSystem;

public class Car {
    private String make;
    private String registrationNumber;

    public Car(String make, String registrationNumber)
    {
        this.setMake(make);
        this.setRegistrationNumber(registrationNumber);
    }

    public String getMake() {
        return this.make;
    }

    private void setMake(String make) {
        this.make = make;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    private void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
