package pokemon;

import java.io.*;
import java.util.HashMap;
import pokemon.util.PokeFile;

public class Pokedex {
    private HashMap<Integer, Pokemon> PokemonHashMap = new HashMap<>();

    public HashMap<Integer, Pokemon> getPokemonHashMap() {
        return PokemonHashMap;
    }

    public void setPokemonHashMap(HashMap<Integer, Pokemon> PokemonHashMap) {
        this.PokemonHashMap = PokemonHashMap;
    }

    public void startPokedex() throws IOException, ClassNotFoundException {
        /*
         *  Runs readPokeFile function and retrieves data from "pokedex_data.txt"
         *  Does not read instantly in case user wants empty Pokedex
         */
        setPokemonHashMap(PokeFile.readPokeFile());
    }

    public void savePokedex() throws IOException {
        PokeFile.writePokeFile(getPokemonHashMap());
    }

    public void addPokemon(Pokemon pokemon) {
        getPokemonHashMap().put(pokemon.getId(), pokemon);
    }

    public void removePokemon(Integer id) {
        getPokemonHashMap().remove(id);
    }

    public Pokemon selectPokemon(Integer id) {
        // Uses ID to return Pokemon object from hashmap and returns null if ID not found
        return getPokemonHashMap().getOrDefault(id, null);
    }

    public void updatePokemon(Integer id, Pokemon modifiedPokemon) {
        getPokemonHashMap().put(id, modifiedPokemon);
    }
}