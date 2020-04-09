package vehicles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));


        String[] carData = buff.readLine().split("\\s+");
        Vehicle car = new Car(Double.parseDouble(carData[1]),Double.parseDouble(carData[2])+0.9,Double.parseDouble(carData[3]));

        String[] truckData = buff.readLine().split("\\s+");
        Vehicle truck = new Truck(Double.parseDouble(truckData[1]),Double.parseDouble(truckData[2])+1.6,Double.parseDouble(truckData[3]));

        String[] busData = buff.readLine().split("\\s+");
        Vehicle bus = new Bus(Double.parseDouble(busData[1]),Double.parseDouble(busData[2]),Double.parseDouble(busData[3]));


        int n = Integer.parseInt(buff.readLine());

        for (int i = 0; i <n ; i++) {
            String[] command = buff.readLine().split("\\s+");

            switch (command[0]){

                case "Drive":

                    if(command[1].equals("Car")){

                        car.drive(Double.parseDouble(command[2]));

                    }else if(command[1].equals("Truck")){

                        truck.drive(Double.parseDouble(command[2]));

                    }else if(command[1].equals("Bus")){

                        bus.setFuelConsumption(bus.getFuelConsumption()+1.4);
                        bus.drive(Double.parseDouble(command[2]));
                        bus.setFuelConsumption(bus.getFuelConsumption()-1.4);
                    }


                    break;

                case"Refuel":

                    if(command[1].equals("Car")){

                        car.refuel(Double.parseDouble(command[2]));

                    }else if(command[1].equals("Truck")){

                        truck.refuel(Double.parseDouble(command[2]));

                    }else if(command[1].equals("Bus")){

                        bus.refuel(Double.parseDouble(command[2]));

                    }

                    break;

                    case "DriveEmpty":

                        bus.drive(Double.parseDouble(command[2]));

                        break;


            }

        }

        System.out.printf("Car: %.2f%n",car.getFuelQuantity());
        System.out.printf("Truck: %.2f%n",truck.getFuelQuantity());
        System.out.printf("Bus: %.2f",bus.getFuelQuantity());

    }
}
