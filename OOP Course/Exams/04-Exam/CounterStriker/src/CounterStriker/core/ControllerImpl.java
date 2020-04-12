package CounterStriker.core;

import CounterStriker.models.field.Field;
import CounterStriker.models.field.FieldImpl;
import CounterStriker.models.guns.Gun;
import CounterStriker.models.guns.Pistol;
import CounterStriker.models.guns.Rifle;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;
import CounterStriker.repositories.GunRepository;
import CounterStriker.repositories.PlayerRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static CounterStriker.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private GunRepository guns;
    private PlayerRepository players;
    private Field field;

    public ControllerImpl() {
        this.guns = new GunRepository();
        this.players = new PlayerRepository();
        this.field = new FieldImpl();
    }

    @Override
    public String addGun(String type, String name, int bulletsCount) {

        Gun gun = null;
        if(type.equals("Pistol")){
            gun = new Pistol(name,bulletsCount);
        }else if(type.equals("Rifle")){
            gun = new Rifle(name,bulletsCount);
        }else{
            throw new IllegalArgumentException(INVALID_GUN_TYPE);
        }

        this.guns.add(gun);
        return String.format("Successfully added gun %s.",name);
    }

    @Override
    public String addPlayer(String type, String username, int health, int armor, String gunName) {

        Gun gun = this.guns.findByName(gunName);

        if(gun == null){
            throw new NullPointerException("Gun cannot be found!");
        }

        Player player = null;

        if(type.equals("Terrorist")){
            player = new Terrorist(username,health,armor,gun);
        }else if(type.equals("CounterTerrorist")){
            player = new CounterTerrorist(username,health,armor,gun);
        }else{
            throw new IllegalArgumentException("Invalid player type!");
        }

        this.players.add(player);
        return String.format("Successfully added player %s.",username);
    }

    @Override
    public String startGame() {

        List<Player> alivePlayers = this.players.getModels().stream()
                .filter(Player::isAlive).collect(Collectors.toList());

        String message = field.start(alivePlayers);

        return message;
    }

    @Override
    public String report() {
        //--------------------------------
        List<Player> collect = this.players.getModels().stream().sorted((a, b) -> {

            int compare = a.getClass().getSimpleName().compareTo(b.getClass().getSimpleName());

            if(compare==0){
                compare = Integer.compare(b.getHealth(), a.getHealth());
            }
            if(compare==0){
                compare = a.getUsername().compareTo(b.getUsername());
            }
            return compare;

        }).collect(Collectors.toList());

        //----------------------------------

        StringBuilder sb = new StringBuilder();

        for (Player player : collect) {

            sb.append(String.format("%s: %s",player.getClass().getSimpleName(),player.getUsername()));
            sb.append(System.lineSeparator());

            sb.append(String.format("--Health: %d",player.getHealth()));
            sb.append(System.lineSeparator());

            sb.append(String.format("--Armor: %d",player.getArmor()));
            sb.append(System.lineSeparator());

            sb.append(String.format("--Gun: %s",player.getGun().getName()));
            sb.append(System.lineSeparator());

        }

        return sb.toString().trim();
    }
}
