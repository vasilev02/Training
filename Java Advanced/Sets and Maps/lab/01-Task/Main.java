package lab;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<String> set = new HashSet<>();

        String input = scanner.nextLine();
        while (!input.equals("END")){
            String[] data = input.split(", ");
            String direction = data[0];
            String plate = data[1];

            switch (direction){
                case"IN":
                    set.add(plate);
                    break;

                case"OUT":
                    if(set.contains(plate)){
                        set.remove(plate);
                    }
                    break;
            }

            input = scanner.nextLine();
        }
        if(set.isEmpty()){
            System.out.println("Parking Lot is Empty");
        }else{
            for (String s : set) {
                System.out.println(s);
            }
        }

    }
}
