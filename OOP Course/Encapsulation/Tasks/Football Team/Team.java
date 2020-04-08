package footballTeam;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String name;
    private List<Player> players;

    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public void removePlayer(String player){

        for (Player current : players) {
            if(current.getName().equals(player)){
                players.remove(current);
                return;
            }
        }

        System.out.printf("Player %s is not in %s team.%n",player,this.getName());

    }

    public double getRating(){
        double resultRating = 0.0;

        for (Player player : this.players) {
            resultRating+=player.overallSkillLevel();
        }
        return Math.round(resultRating);
    }

}
