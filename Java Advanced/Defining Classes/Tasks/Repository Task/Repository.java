package repository;

import java.util.LinkedHashMap;
import java.util.Map;

public class Repository {

    private int ID=0;

    private Map<Integer,Person> data;

    public Repository(){
        this.data = new LinkedHashMap<>();
    }

    public void add(Person person){
        this.data.put(ID,person);
        ID++;
    }

    public Person get(int id){
        return this.data.get(id);
    }

    public boolean update(int id,Person newPerson){

        if(data.containsKey(id)){
            data.put(id,newPerson);
            return true;
        }
        return false;
    }

    public boolean delete(int id){

        if(data.containsKey(id)){
            data.remove(id);
            return true;
        }
        return false;
    }

    public int getCount(){
        return this.data.size();
    }


}
