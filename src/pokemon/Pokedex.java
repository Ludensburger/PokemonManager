package pokemon;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import pokemon.util.PokeFile;

public class Pokedex {
    private ArrayList<Pokemon> pokemonArrayList;

    public ArrayList<Pokemon> getPokemonArrayList() {
        return pokemonArrayList;
    }
    public void setPokemonArrayList(ArrayList<Pokemon> pokemonArrayList) {
        this.pokemonArrayList = pokemonArrayList;
    }

    public void startPokedex() throws FileNotFoundException {
        setPokemonArrayList(PokeFile.readPokeFile());
    }

    public void addPokemon(Pokemon pokemon) {
        getPokemonArrayList().add(pokemon);
    }

    public void removePokemon(Pokemon pokemon) {
        getPokemonArrayList().remove(pokemon);
    }

    public Pokemon selectPokemon(int id) {
        for(Pokemon pokemon : getPokemonArrayList()) {
            if(id == pokemon.getId()) {
                return pokemon;
            }
        }
        return null;
    }

    public Pokemon selectPokemon(String name) {
        for(Pokemon pokemon : getPokemonArrayList()) {
            if(Objects.equals(name, pokemon.getName())) {
                return pokemon;
            }
        }
        return null;
    }
}