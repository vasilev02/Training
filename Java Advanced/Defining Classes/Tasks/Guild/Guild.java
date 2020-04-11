package guild;

import java.util.ArrayList;
import java.util.List;

public class Guild {

    private String name;
    private int capacity;
    private List<Player> roster;

    public String getName() {
        return this.name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Guild(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        roster = new ArrayList<>(capacity);
    }

    public void addPlayer(Player player){

        if(this.roster.size()+1<=this.capacity){
            this.roster.add(player);
        }
    }

    public boolean removePlayer(String name){

        for (int i = 0; i <this.roster.size() ; i++) {
            if(this.roster.get(i).getName().equals(name)){
                this.roster.remove(i);
                return true;
            }
        }
        return false;
    }

    public void promotePlayer(String name){

        for (int i = 0; i <this.roster.size() ; i++) {

            if(this.roster.get(i).getName().equals(name)){
                if(!this.roster.get(i).getRank().equals("Member")){
                    this.roster.get(i).setRank("Member");
                    break;
                }
            }
        }
    }

    public void demotePlayer(String name){

        for (int i = 0; i <this.roster.size() ; i++) {
            if(this.roster.get(i).getName().equals(name)){
                this.roster.get(i).setRank("Trial");
                break;
            }
        }
    }

    public Player[] kickPlayersByClass(String clazz){

        List<Player> players = new ArrayList<>();

        for (int i = 0; i <this.roster.size() ; i++) {
            if(this.roster.get(i).getClazz().equals(clazz)){
                players.add(this.roster.get(i));
                this.roster.remove(i);
                i--;
            }
        }
        Player[] array = new Player[players.size()];
        for (int i = 0; i <array.length ; i++) {
            array[i]=players.get(i);
        }

        return array;
    }

    public int count(){
        return this.roster.size();
    }

    public String report(){

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Players in the guild: %s:",this.getName()));
        sb.append(System.lineSeparator());
        for (Player player : roster) {
            sb.append(player.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

}
