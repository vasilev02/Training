package heroRepository;

import java.util.ArrayList;
import java.util.List;

public class HeroRepository {

    List<Hero> data;

    public HeroRepository(){
        data = new ArrayList<>();
    }

    public void add(Hero entity){
        data.add(entity);
    }

    public void remove(String name){

        for (int i = 0; i <data.size() ; i++) {
            Hero hero = data.get(i);
            if(hero.getName().equals(name)){
                data.remove(i);
                break;
            }
        }

    }

    public Hero getHeroWithHighestStrength(){

        int counter = Integer.MIN_VALUE;
        Hero toReturn = null;

        for (int i = 0; i <data.size() ; i++) {
            Hero hero = data.get(i);
            if(hero.getItem().getStrength()>counter){
                toReturn=hero;
                counter=hero.getItem().getStrength();
            }
        }
        return toReturn;
    }

    public Hero getHeroWithHighestAgility(){

        int counter = Integer.MIN_VALUE;
        Hero toReturn = null;

        for (int i = 0; i <data.size() ; i++) {
            Hero hero = data.get(i);
            if(hero.getItem().getAgility()>counter){
                toReturn=hero;
                counter=hero.getItem().getAgility();
            }
        }
        return toReturn;
    }

    public Hero getHeroWithHighestIntelligence(){

        int counter = Integer.MIN_VALUE;
        Hero toReturn = null;

        for (int i = 0; i <data.size() ; i++) {
            Hero hero = data.get(i);
            if(hero.getItem().getIntelligence()>counter){
                toReturn=hero;
                counter=hero.getItem().getIntelligence();
            }
        }
        return toReturn;
    }

    public int getCount(){
        return this.data.size();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (Hero currentHero : data) {
            sb.append(currentHero.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
