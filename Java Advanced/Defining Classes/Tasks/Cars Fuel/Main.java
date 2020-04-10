package carsFuel;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String,Car> allCars = new LinkedHashMap<>();

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i <n ; i++) {
            String[] data = scanner.nextLine().split("\\s+");

            String carName = data[0];
            double money = Double.parseDouble(data[1]);
            double costKM = Double.parseDouble(data[2]);

            Car current = new Car(carName,money,costKM,0);
            allCars.put(carName,current);
        }

        String input = scanner.nextLine();
        while (!input.equals("End")){

            String[] data = input.split("\\s+");
            String name = data[1];
            int amountKM = Integer.parseInt(data[2]);

            Car current = allCars.get(name);
            double price = current.getCostKM();

            double sum = current.drive(amountKM,price);

            input = scanner.nextLine();
        }

        for (Map.Entry<String, Car> current : allCars.entrySet()) {

            Car car = current.getValue();

            System.out.println(car.toString());

        }

    }
}
