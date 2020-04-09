package exercise;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Character,Integer> mapping = new TreeMap<>();

        String text = scanner.nextLine();

        for (int i = 0; i <text.length() ; i++) {
            char symbol = text.charAt(i);

            if(mapping.containsKey(symbol)){
                Integer value = mapping.get(symbol);
                mapping.put(symbol,value+1);
            }else{
                mapping.put(symbol,1);
            }

        }

        mapping.entrySet().forEach(entry->{

            System.out.printf("%s: %d time/s%n",entry.getKey(),entry.getValue());

        });


    }
}
