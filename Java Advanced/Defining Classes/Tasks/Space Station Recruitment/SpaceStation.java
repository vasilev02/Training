package spaceStationRecruitment;

import java.util.ArrayList;
import java.util.List;

public class SpaceStation {

    private String name;
    private int capacity;
    private List<Astronaut> data;

    public SpaceStation(String name,int capacity){
        this.name=name;
        this.capacity=capacity;
        data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCount(){
        return data.size();
    }

    public void add(Astronaut astronaut){

        if(getCount()<getCapacity()){
            data.add(astronaut);
        }
    }

    public boolean remove(String name){

        boolean result=false;

        for (int i = 0; i <data.size() ; i++) {
            Astronaut astronaut = data.get(i);
            if(astronaut.getName().equals(name)){
                data.remove(i);
                result = true;
                break;
            }
        }

        return result;
    }

    public Astronaut getOldestAstronaut(){

        Astronaut astronaut = null;
        int current = Integer.MIN_VALUE;

        for (int i = 0; i <data.size() ; i++) {
            Astronaut currentAstronaut = data.get(i);
            if(currentAstronaut.getAge()>current){
                astronaut = currentAstronaut;
                current=currentAstronaut.getAge();
            }
        }
        return astronaut;
    }

    public Astronaut getAstronaut(String name){

        Astronaut astronaut = null;

        for (int i = 0; i <data.size() ; i++) {
            Astronaut current = data.get(i);
            if(current.getName().equals(name)){
                astronaut = current;
                return astronaut;
            }
        }
        return astronaut;
    }

    public String report(){

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Astronauts working at Space Station %s:%n",getName()));

        for (int i = 0; i <data.size() ; i++) {
            Astronaut astronaut = data.get(i);
            sb.append(astronaut.toString()).append(System.lineSeparator());
        }
        return sb.toString().trim();

    }

}
