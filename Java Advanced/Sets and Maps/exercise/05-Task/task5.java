package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String,String> mapping = new HashMap<>();

        String input = scanner.nextLine();
        while (!input.equals("search")){

            String[] data = input.split("\\-");
            String name = data[0];
            String number = data[1];

            if(mapping.containsKey(name)){
                mapping.put(name,number);
            }else{
                mapping.put(name,number);
            }

            input = scanner.nextLine();
        }

        String secondInput = scanner.nextLine();
        while (!secondInput.equals("stop")){

            if(mapping.containsKey(secondInput)){
                String valueNumber = mapping.get(secondInput);
                System.out.printf("%s -> %s%n",secondInput,valueNumber);
            }else{
                System.out.printf("Contact %s does not exist.%n",secondInput);
            }

            secondInput = scanner.nextLine();
        }


    }
}
