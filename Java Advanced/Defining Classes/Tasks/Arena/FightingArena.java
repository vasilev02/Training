package arena;

import java.util.ArrayList;
import java.util.List;

public class FightingArena {

    private String name;
    private List<Gladiator> gladiators;

    public FightingArena(String name) {
        this.name = name;
        this.gladiators = new ArrayList<>();
    }

    public void add(Gladiator entity) {
        gladiators.add(entity);
    }

    public void remove(String name){

        for (int i = 0; i <gladiators.size() ; i++) {
            Gladiator current = gladiators.get(i);
            if(current.getName().equals(name)){
                gladiators.remove(i);
                break;
            }
        }

    }

    public Gladiator getGladiatorWithHighestStatPower(){
        Gladiator gladiator = null;
        int counter = Integer.MIN_VALUE;

        for (Gladiator current : gladiators) {
            if(current.getStatPower()>counter){
                gladiator=current;
                counter=current.getStatPower();
            }
        }
        return gladiator;
    }

    public Gladiator getGladiatorWithHighestWeaponPower(){
        Gladiator gladiator = null;
        int counter = Integer.MIN_VALUE;

        for (Gladiator current : gladiators) {
            if(current.getWeaponPower()>counter){
                gladiator=current;
                counter=current.getWeaponPower();
            }
        }
        return gladiator;
    }

    public Gladiator getGladiatorWithHighestTotalPower(){
        Gladiator gladiator = null;
        int counter = Integer.MIN_VALUE;

        for (Gladiator current : gladiators) {
            if(current.getTotalPower()>counter){
                gladiator=current;
                counter=current.getTotalPower();
            }
        }
        return gladiator;
    }

    public int getCount(){
        return this.gladiators.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s – %d gladiators are participating.",this.name,getCount()));
        sb.append(System.lineSeparator());

        return sb.toString().trim();
    }
}
