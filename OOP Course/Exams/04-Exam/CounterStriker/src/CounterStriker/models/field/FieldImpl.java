package CounterStriker.models.field;

import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FieldImpl implements Field {


    public FieldImpl() {
    }

    @Override
    public String start(Collection<Player> players) {

        List<Player> terrorists = players.stream().filter
                (p -> p.getClass().getSimpleName().equals("Terrorist"))
                .collect(Collectors.toList());

        List<Player> counters = players.stream().filter
                (p -> p.getClass().getSimpleName().equals("CounterTerrorist"))
                .collect(Collectors.toList());

        String message = "";

        while (true) {

            for (Player terrorist : terrorists) {

                if(!terrorist.isAlive()){
                    continue;
                }

                for (Player counter : counters) {

                    if (counter.isAlive()) {
                        counter.takeDamage(terrorist.getGun().fire());
                    }
                }
            }

            if(!checkingPlayers(counters)){
                message = "Terrorist wins!";
                break;
            }

            for (Player counter : counters) {

                if(!counter.isAlive()){
                    continue;
                }

                for (Player terrorist : terrorists) {

                    if (terrorist.isAlive()) {
                        terrorist.takeDamage(counter.getGun().fire());
                    }
                }
            }
            if(!checkingPlayers(terrorists)){
                message =  "Counter Terrorist wins!";
                break;
            }

        }


        return message;
    }

    private static boolean checkingPlayers(List<Player> players){

        boolean check = false;

        for (Player player : players) {
            if(player.isAlive()){
                check = true;
                break;
            }
        }

        return check;
    }

}
