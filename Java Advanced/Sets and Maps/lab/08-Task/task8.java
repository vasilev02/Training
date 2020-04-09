package lab;

import java.util.*;

public class task8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String,Double> mapping = new TreeMap<>();

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i <n ; i++) {

            String name = scanner.nextLine();
            String[] grades = scanner.nextLine().split("\\s+");
            double sum = 0.0;
            for (int j = 0; j <grades.length ; j++) {
                sum+= Double.parseDouble(grades[j]);
            }
            double result = sum/grades.length;

            mapping.put(name,result);

        }

        mapping.entrySet().stream().forEach(entry->{

            System.out.printf("%s is graduated with ",entry.getKey());
            System.out.println(entry.getValue());

        });

    }
}
