package lab;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Double,Integer> map = new LinkedHashMap<>();

        String[] data = scanner.nextLine().split("\\s+");

        for (int i = 0; i <data.length ; i++) {
            double number = Double.parseDouble(data[i]);

            if(map.containsKey(number)){
                Integer value = map.get(number);
                map.put(number,value+1);
            }else{
                map.put(number,1);
            }

        }
        for (Map.Entry<Double, Integer> entry : map.entrySet()) {
            System.out.printf("%.1f -> %d%n",entry.getKey(),entry.getValue());
        }


    }
}
