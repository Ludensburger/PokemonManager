import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Pokemon {
    private String name;
    private String type;

    public Pokemon(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    static void showPokemonList(ArrayList<Pokemon> pokemonList) {
        if (pokemonList.isEmpty()) {
            System.out.println("No Pokemon in the list.");
        } else {
            System.out.println("Pokemon List:");
            for (Pokemon pokemon : pokemonList) {
                System.out.println("Name: " + pokemon.getName() + ", Type: " + pokemon.getType());
            }
        }
    }

    static void sortByName(ArrayList<Pokemon> pokemonList) {
        Collections.sort(pokemonList, Comparator.comparing(Pokemon::getName));
        System.out.println("Pokemon list sorted by name.");
    }

    static void sortByType(ArrayList<Pokemon> pokemonList) {
        Collections.sort(pokemonList, Comparator.comparing(Pokemon::getType));
        System.out.println("Pokemon list sorted by type.");
    }
}