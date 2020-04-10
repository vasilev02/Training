package healthyHeaven;

import java.util.ArrayList;
import java.util.List;

public class Salad {
    private List<Vegetable> products;
    private String name;


    public Salad(String name){
        this.name = name;
        this.products = new ArrayList<>();
    }

    public String getName(){
        return this.name;
    }

    public int getTotalCalories(){
        int sum = 0;
        for (Vegetable product : products) {
            sum+=product.getCalories();
        }
        return sum;
    }

    public int getProductCount(){
        return products.size();
    }

    public void add(Vegetable product){
        products.add(product);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("* Salad %s is %d calories and have %d products:",getName(),getTotalCalories(),getProductCount()));
        sb.append(System.lineSeparator());

        for (Vegetable product : products) {
            sb.append(product.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}