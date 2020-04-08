package shoppingSpree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);


        try {

            List<String> people = Arrays.stream(scanner.nextLine().split("\\;")).collect(Collectors.toList());
            List<Person> persons = new ArrayList<>();

            for (String current : people) {
                String[] currentData = current.split("\\=");
                Person person = new Person(currentData[0], Double.parseDouble(currentData[1]));
                persons.add(person);
            }


            List<String> productsData = Arrays.stream(scanner.nextLine().split("\\;")).collect(Collectors.toList());
            List<Product> products = new ArrayList<>();

            for (String current : productsData) {
                String[] currentData = current.split("\\=");
                Product product = new Product(currentData[0], Double.parseDouble(currentData[1]));
                products.add(product);
            }
            String[] command = scanner.nextLine().split("\\s+");

            while (!command[0].equals("END")) {

                String personName = command[0];
                String productName = command[1];

                Person person = null;
                for (Person current : persons) {
                    if (current.getName().equals(personName)) {
                        person = current;
                        break;
                    }
                }

                Product product = null;
                for (Product current : products) {
                    if (current.getName().equals(productName)) {
                        product = current;
                        break;
                    }
                }

                if (person != null && product != null) {
                    person.buyProduct(product);
                }

                command = scanner.nextLine().split("\\s+");
            }

            for (Person current : persons) {
                System.out.println(current.toString());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
