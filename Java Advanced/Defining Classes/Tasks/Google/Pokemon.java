package google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pokemon {
    private List<String> pokemonList;

    public Pokemon() {
        this.pokemonList= new ArrayList<>();
    }

    public void add(String token){
        this.pokemonList.add(token);
    }

    public List<String> getPokemonList() {
        return Collections.unmodifiableList(pokemonList);
    }
}
