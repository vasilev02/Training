package shoppingSpree;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private String name;
    private double money;
    private List<Product> products;



    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.products = new ArrayList<>();
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }else{
            this.name = name;
        }

    }

    private void setMoney(double money) {
        if(money<0){
            throw new IllegalArgumentException("Cost cannot be negative");
        }else{
            this.money = money;
        }

    }

    public String getName() {
        return this.name;
    }

    public void buyProduct(Product product){

        if(this.money>=product.getCost()){
            System.out.printf("%s bought %s%n",this.getName(),product.getName());
            products.add(product);
            money-=product.getCost();
        }else{
            System.out.printf("%s can't afford %s%n",this.getName(),product.getName());
        }

    }

    @Override
    public String toString() {

        if(this.products.isEmpty()){
            return String.format("%s - Nothing bought",this.getName());
        }

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s - ",this.getName()));

        for (int i = 0; i <this.products.size() ; i++) {
            if(i==products.size()-1){
                sb.append(this.products.get(i).getName());
            }else{
                sb.append(this.products.get(i).getName()+", ");
            }
        }

        sb.append(System.lineSeparator());
        return sb.toString().trim();

    }
}
