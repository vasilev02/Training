package vehicles;

import java.text.DecimalFormat;

public class Truck extends Vehicle {


    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
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

            System.out.println("Truck travelled "+decimalFormat.format(distance)+" km");

        }else{
            System.out.println("Truck needs refueling");
        }

    }

    public void refuel(double liters) {

        if(liters<=0){
            System.out.println("Fuel must be a positive number");
            return;
        }

        if(getFuelQuantity()+liters<=getTankCapacity()){
            this.setFuelQuantity(getFuelQuantity()+(liters*0.95));
        }else{
            System.out.println("Cannot fit fuel in tank");
        }


    }


}
