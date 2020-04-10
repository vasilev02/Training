package rawData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String,Car> fragileMap = new LinkedHashMap<>();
        Map<String,Car> flamableMap = new LinkedHashMap<>();

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i <n ; i++) {

            String[] data = scanner.nextLine().split("\\s+");
            String name = data[0];
            int enginePower = Integer.parseInt(data[2]);
            int engineWeight = Integer.parseInt(data[3]);
            String cargoType = data[4];
            boolean check = false;
            double tire1 = Double.parseDouble(data[5]);
            double tire2 = Double.parseDouble(data[7]);
            double tire3 = Double.parseDouble(data[9]);
            double tire4 = Double.parseDouble(data[11]);

            double average=0.0;
            if(tire1<1||tire2<1||tire3<1||tire4<1){
                average = 0.5;
            }else{
                average=2;
            }


            Car current = new Car(name,enginePower,engineWeight,cargoType,average);
            
            if(cargoType.equals("flamable")){
                flamableMap.put(name,current);
            }else{
                fragileMap.put(name,current);
            }
        }
        String input = scanner.nextLine();
        
        if(input.equals("flamable")){

            for (Map.Entry<String, Car> current : flamableMap.entrySet()) {

                Car car = current.getValue();

                if(car.getEnginePower()>250){
                    System.out.println(current.getKey());
                }

            }
            
        }else{


            for (Map.Entry<String, Car> current : fragileMap.entrySet()) {


                Car car = current.getValue();

                if(car.getAverage()<1){
                    System.out.println(current.getKey());
                }


            }


        }

    }
}
