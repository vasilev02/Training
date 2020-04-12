package CounterStriker.repositories;

import CounterStriker.models.guns.Gun;
import CounterStriker.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PlayerRepository implements Repository<Player> {

    private Collection<Player> models;

    public PlayerRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<Player> getModels() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Player model) {
        if(model==null){
            throw new NullPointerException("Cannot add null in Player Repository.");
        }
        this.models.add(model);
    }

    @Override
    public boolean remove(Player model) {

        for (Player player : models) {
            if(player.getUsername().equals(model.getUsername())){
                this.models.remove(player);
                return true;
            }
        }
        return false;
    }

    @Override
    public Player findByName(String name) {
        Player player = this.models.stream().filter(m -> m.getUsername().equals(name)).findFirst().orElse(null);
        return player;
    }
}
