package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class task6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String,Long> mapping = new LinkedHashMap<>();

        String input = scanner.nextLine();
        while (!input.equals("stop")){

            long quantity = Long.parseLong(scanner.nextLine());

            if(mapping.containsKey(input)){
                long value = mapping.get(input);
                mapping.put(input,value+quantity);
            }else{
                mapping.put(input,quantity);
            }

            input = scanner.nextLine();
        }

        mapping.entrySet().stream().forEach(entry->{

            System.out.printf("%s -> ",entry.getKey());
            System.out.println(entry.getValue());

        });


    }
}
