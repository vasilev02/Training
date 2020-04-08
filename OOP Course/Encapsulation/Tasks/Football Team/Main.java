package footballTeam;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Set<Team> teams = new LinkedHashSet<>();

        String[] sc = scanner.nextLine().split("\\;");

        while (!sc[0].equals("END")){

            switch (sc[0]){

                case "Team":
                    try {
                        Team team = new Team(sc[1]);
                        teams.add(team);
                    }catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }

                    break;

                case"Add":

                    try {

                        String teamName = sc[1];

                        boolean find = false;
                        for (Team currentTeam : teams) {
                            if (currentTeam.getName().equals(teamName)) {
                                find = true;

                                String playerName = sc[2];
                                int endurance = Integer.parseInt(sc[3]);
                                int sprint = Integer.parseInt(sc[4]);
                                int dribble = Integer.parseInt(sc[5]);
                                int passing = Integer.parseInt(sc[6]);
                                int shooting = Integer.parseInt(sc[7]);

                                Player player = new Player(playerName, endurance, sprint,
                                        dribble, passing, shooting);

                                currentTeam.addPlayer(player);
                                break;
                            }
                        }

                        if (find == false) {
                            System.out.printf("Team %s does not exist.%n", teamName);
                        }
                    }catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }

                    break;

                case "Remove":

                    String teamN = sc[1];
                    String playerN = sc[2];

                    Team currentTeam = null;
                    Player currentPlayer = null;

                    for (Team current : teams) {
                        if(current.getName().equals(teamN)){
                            currentTeam = current;
                            break;
                        }
                    }
                    if(currentTeam==null){
                        System.out.printf("Team %s does not exist.%n", teamN);
                    }

                    if(currentTeam!=null){

                        currentTeam.removePlayer(playerN);

                    }
                    break;

                case "Rating":

                    String name = sc[1];

                    boolean check = false;

                    for (Team current : teams) {
                        if(current.getName().equals(name)){
                            System.out.printf("%s - ",current.getName());
                            System.out.println((int)current.getRating());
                            check = true;
                        }
                    }
                    if(check==false) {
                        System.out.printf("Team %s does not exist.%n",name);
                    }
                    break;

            }

            sc = scanner.nextLine().split("\\;");
        }


    }
}
