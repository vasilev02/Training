package lab;

import java.util.*;

public class task6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String,Map<String,Double>>  mapping = new TreeMap<>();

        String input = scanner.nextLine();
        while (!input.equals("Revision")){
            String[] data = input.split(", ");
            String name = data[0];
            String product = data[1];
            double price = Double.parseDouble(data[2]);

            if(mapping.containsKey(name)){
                Map<String,Double> current = mapping.get(name);
                current.put(product,price);
                mapping.put(name,current);
            }else{
                Map<String,Double> current = new LinkedHashMap<>();
                current.put(product,price);
                mapping.put(name,current);
            }

            input = scanner.nextLine();
        }

        mapping.entrySet().stream().forEach(entry->{

            System.out.println(entry.getKey()+"->");

            Map<String,Double> current = mapping.get(entry.getKey());

            for (Map.Entry<String, Double> tokens : current.entrySet()) {
                System.out.printf("Product: %s, Price: %.1f%n",tokens.getKey(),tokens.getValue());
            }

        });

    }
}
