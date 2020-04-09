package exercise;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<Integer> firstSet = new LinkedHashSet<>();
        Set<Integer> secondSet = new LinkedHashSet<>();

        String[] data = scanner.nextLine().split("\\s+");
        int n1 = Integer.parseInt(data[0]);
        int n2 = Integer.parseInt(data[1]);

        for (int i = 0; i <n1 ; i++) {
            int number = Integer.parseInt(scanner.nextLine());
            firstSet.add(number);
        }
        for (int i = 0; i <n2 ; i++) {
            int number = Integer.parseInt(scanner.nextLine());
            secondSet.add(number);
        }

        for (Integer current : firstSet) {
            if(secondSet.contains(current)){
                System.out.print(current+" ");
            }
        }


    }
}
