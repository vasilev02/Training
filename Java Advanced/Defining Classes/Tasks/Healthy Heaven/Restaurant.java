package healthyHeaven;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private List<Salad> data;
    private String name;


    public Restaurant(String name){
        this.name = name;
        this.data = new ArrayList<>();
    }

    public void add(Salad salad){
        this.data.add(salad);
    }

    public boolean buy(String name){

        for (int i = 0; i <data.size() ; i++) {
            Salad salad = data.get(i);
            if(salad.getName().equals(name)){
                data.remove(i);
                return true;
            }
        }
        return false;
    }

    public Salad getHealthiestSalad(){
        Salad salad = null;
        int value = Integer.MAX_VALUE;

        for (Salad current : data) {
            if(current.getTotalCalories()<value){
                salad = current;
            }
        }
        return salad;
    }

    public String generateMenu() {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s have %d salads:",this.name,data.size()));
        sb.append(System.lineSeparator());

        for (Salad currentSalad : data) {
            sb.append(currentSalad.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}