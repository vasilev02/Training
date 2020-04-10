package carSalesman;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Engine> engineList = new ArrayList<>();
        List<Car> carList = new ArrayList<>();

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i <n ; i++) {

            String[] engineData = scanner.nextLine().split("\\s+");

            String model = engineData[0];
            int power = Integer.parseInt(engineData[1]);
            Engine currentEngine = new Engine(model,power);

            if(engineData.length==4){
                currentEngine.setDisplacement(engineData[2]);
                currentEngine.setEfficiency(engineData[3]);
            }else if(engineData.length==3){

                char value = engineData[2].charAt(0);

                if(Character.isDigit(value)){
                    currentEngine.setDisplacement(engineData[2]);
                    currentEngine.setEfficiency("n/a");
                }else{
                    currentEngine.setDisplacement("n/a");
                    currentEngine.setEfficiency(engineData[2]);
                }

            }else{
                currentEngine.setDisplacement("n/a");
                currentEngine.setEfficiency("n/a");
            }
            engineList.add(currentEngine);
        }

        int m = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i <m ; i++) {

            String[] carData = scanner.nextLine().split("\\s+");

            String model = carData[0];
            String engine = carData[1];

            Car car = new Car(model,engine);

            if(carData.length==4){
                car.setWeight(carData[2]);
                car.setColor(carData[3]);
            }else if(carData.length==3){

                char value = carData[2].charAt(0);
                if(Character.isDigit(value)){
                    car.setWeight(carData[2]);
                    car.setColor("n/a");
                }else{
                    car.setWeight("n/a");
                    car.setColor(carData[2]);
                }

            }else{
                car.setWeight("n/a");
                car.setColor("n/a");
            }

            for (Engine token : engineList) {

                if(token.getModel().equals(engine)){
                    car.setCurrentEngine(token);
                    break;
                }

            }
            carList.add(car);
        }

        for (Car currentCar : carList) {

            System.out.println(currentCar.getModel()+":");
            System.out.println(currentCar.getCurrentEngine().getModel()+":");
            System.out.printf("Power: %s%n",currentCar.getCurrentEngine().getPower());
            System.out.printf("Displacement: %s%n",currentCar.getCurrentEngine().getDisplacement());
            System.out.printf("Efficiency: %s%n",currentCar.getCurrentEngine().getEfficiency());
            System.out.printf("Weight: %s%n",currentCar.getWeight());
            System.out.printf("Color: %s%n",currentCar.getColor());

        }

    }
}
