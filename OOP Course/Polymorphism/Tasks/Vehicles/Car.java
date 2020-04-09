package vehicles;

import java.text.DecimalFormat;

public class Car extends Vehicle {


    public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    @Override
    public void drive(double distance) {

        if(distance<=0){
            System.out.println("Fuel must be a positive number");
            return;
        }

        if(getFuelQuantity()>= getFuelConsumption()*distance){
            this.setFuelQuantity(getFuelQuantity()-getFuelConsumption()*distance);

            DecimalFormat decimalFormat = new DecimalFormat("0.##");

            System.out.println("Car travelled "+decimalFormat.format(distance)+" km");
        }else{
            System.out.println("Car needs refueling");
        }

    }

    @Override
    public void refuel(double liters) {


        if(liters<=0){
            System.out.println("Fuel must be a positive number");
            return;
        }

        if(getFuelQuantity()+liters<=getTankCapacity()){
            this.setFuelQuantity(getFuelQuantity()+liters);
        }else{
            System.out.println("Cannot fit fuel in tank");
        }


    }


}
