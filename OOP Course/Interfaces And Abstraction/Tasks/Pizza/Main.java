package piza;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] pizzaLine = scanner.nextLine().split("\\s+");
        String pizzaName = pizzaLine[1];
        int numberOfToppings = Integer.parseInt(pizzaLine[2]);
        Pizza pizza = new Pizza(pizzaName, numberOfToppings);

        String[] doughLine = scanner.nextLine().split("\\s+");
        String flourType = doughLine[1];
        String bakingTechnique = doughLine[2];
        double weight = Double.parseDouble(doughLine[3]);
        Dough dough = new Dough(flourType, bakingTechnique, weight);

        pizza.setDough(dough);

        String input;
        while (!"END".equals(input = scanner.nextLine())){
            String[] tokens = input.split("\\s+");
            String toppingType = tokens[1];
            double weightt = Double.parseDouble(tokens[2]);

            Topping topping = new Topping(toppingType, weightt);
            pizza.addTopping(topping);

        }
        pizza.getOverallCalories();

    }
}
