package piza;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private Dough dough;
    private List<Topping> toppings;

    public Pizza(String name, int toppings) {
        this.setName(name);
        this.setToppings(toppings);
    }

    private void setToppings(int toppings) {
        if (toppings < 0 || toppings > 10) {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
        this.toppings = new ArrayList<>(toppings);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.trim().length() > 15) {
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }

        this.name = name;
    }

    public void setDough(Dough dough) {

        this.dough = dough;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public void getOverallCalories() {


        double result = this.dough.calculateCalories() + this.toppings.stream()
                .mapToDouble(Topping::calculateCalories)
                .sum();

        System.out.println(String.format("%s - %.2f",this.name,result));

    }
}