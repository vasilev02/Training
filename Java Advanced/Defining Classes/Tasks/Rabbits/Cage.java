package rabbits;

import java.util.ArrayList;
import java.util.List;

public class Cage {

    private String name;
    private int capacity;
    private List<Rabbit> data;

    public Cage(String name,int capacity){
        this.name=name;
        this.capacity=capacity;
        data = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int count(){
        return data.size();
    }

    public void add(Rabbit rabbit){

        if(count()<getCapacity()){
            data.add(rabbit);
        }
    }

    public boolean removeRabbit(String name){

        boolean result = false;

        for (int i = 0; i <data.size() ; i++) {
            Rabbit rabbit = data.get(i);
            if(rabbit.getName().equals(name)){
                data.remove(i);
                result=true;
                break;
            }
        }
        return result;
    }

    public void removeSpecies(String species){

        for (int i = 0; i <data.size() ; i++) {
            Rabbit rabbit = data.get(i);
            if(rabbit.getSpecies().equals(species)){
                data.remove(i);
                i--;
            }
        }
    }

    public Rabbit sellRabbit(String name){
        Rabbit rabbit = null;
        for (Rabbit current : data) {
            if(current.getName().equals(name)){
                current.setAvailable(false);
                rabbit = current;
            }
        }
        return rabbit;
    }

    public List<Rabbit> sellRabbitBySpecies(String species){

        List<Rabbit> list = new ArrayList<>();

        for (Rabbit rabbit : data) {
            if(rabbit.getSpecies().equals(species)){
                rabbit.setAvailable(false);
                list.add(rabbit);
            }
        }
        return list;
    }

    public String report(){

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Rabbits available at %s:",getName()));
        sb.append(System.lineSeparator());

        for (Rabbit rabbit : data) {
            if(rabbit.isAvailable()==true){
                sb.append(rabbit.toString());
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }


}
