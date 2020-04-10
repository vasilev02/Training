package christmas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Bag {

    private String color;
    private int capacity;
    private List<Present> data;

    public int count() {
        return data.size();
    }

    public String getColor() {
        return this.color;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public Bag(String color, int capacity) {
        this.color = color;
        this.capacity = capacity;
        data = new ArrayList<>(capacity);
    }

    public void add(Present present){

        if(this.data.size()<capacity){
            this.data.add(present);
        }
    }

    public boolean remove(String name){

        for (Present current : data) {
            if(current.getName().equals(name)){
                this.data.remove(current);
                return true;
            }
        }
        return false;
    }

    public Present heaviestPresent(){

        Present present = null;
        double value = Double.MIN_VALUE;

        for (Present current : data) {
            if(current.getWeight()>value){
                value = current.getWeight();
                present = current;
            }
        }
        return present;
    }

    public Present getPresent(String name){

        for (Present datum : data) {
            if(datum.getName().equals(name)){
                return datum;
            }
        }
        return null;
    }


    public String report() {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s bag contains:",this.color));
        sb.append(System.lineSeparator());
        for (Present current : data) {
            sb.append(String.format("Present %s for a %s",current.getName(),current.getGender()));
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
