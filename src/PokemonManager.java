import pokemon.Pokedex;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;
import pokemon.*;
import pokemon.util.PokeFile;

public class PokemonManager {
    public static void main(String[] args) throws IOException {
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
        Scanner scanner = new Scanner(System.in);
        Pokedex pokedex = new Pokedex();
        pokedex.startPokedex();
        int choice;

        String name, desc, bool, type, idString;

        do {
            System.out.println("Menu:");
            System.out.println("1. Add Pokemon");
            System.out.println("2. Show Pokemon List");
            System.out.println("3. Sort by Name");
            System.out.println("4. Sort by Type");
            System.out.println("5. Select Pokemon");
            System.out.println("6. Update Pokedex");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter name of pokemon: ");
                    name = scanner.next().toUpperCase();

                    System.out.print("Describe your pokemon: ");
                    desc = scanner.next().toUpperCase();

                    System.out.print("Does your pokemon have two types? [Y/N]: ");
                    scanner.nextLine();

                    bool = scanner.next();

                    if(Objects.equals(bool, "y") || Objects.equals(bool, "Y")) {
                        String firstType = String.valueOf(PokemonType.pickType());
                        String secondType;
                        do {
                             secondType = String.valueOf(PokemonType.pickType());
                            if(Objects.equals(firstType, secondType)) {
                                System.out.println("A pokemon can't have two of the same type!");
                            }
                        } while(Objects.equals(firstType, secondType));
                        type = firstType + "/" + secondType;
                    } else {
                        type = String.valueOf(PokemonType.pickType());
                    }

                    int id = pokedex.getPokemonArrayList().size() + 1;

                    pokedex.addPokemon(new Pokemon.PokemonBuilder(name, id, desc, type).build());
                    break;
                case 2:
                    if(pokedex.getPokemonArrayList().isEmpty()) {
                        System.out.println("Pokedex is empty!");
                        break;
                    }

                    System.out.println("Pokemon List:");
                    for(Pokemon pokemon : pokedex.getPokemonArrayList()) {
                        name = pokemon.getName();
                        idString = "#" + String.format("%03d", pokemon.getId());
                        type = pokemon.getType();

                        System.out.println(idString + "-" + name + "\t\t" + "TYPE: " + type);
                    }
                    break;
                case 3:
                    if(pokedex.getPokemonArrayList().isEmpty()) {
                        System.out.println("Pokedex is empty!");
                        break;
                    }

                    ArrayList<Pokemon> sortedPokemonListByName = pokedex.getPokemonArrayList();
                    sortedPokemonListByName.sort(Comparator.comparing(Pokemon::getName));
                    System.out.println("Pokemon List - Sorted By Name:");
                    for(Pokemon pokemon : sortedPokemonListByName) {
                        name = pokemon.getName();
                        idString = "#" + String.format("%03d", pokemon.getId());
                        type = pokemon.getType();

                        System.out.println(idString + "-" + name + "\t\t" + "TYPE: " + type);
                    }
                    break;
                case 4:
                    if(pokedex.getPokemonArrayList().isEmpty()) {
                        System.out.println("Pokedex is empty!");
                        break;
                    }

                    ArrayList<Pokemon> sortedPokemonListByType = pokedex.getPokemonArrayList();
                    sortedPokemonListByType.sort(Comparator.comparing(Pokemon::getType));
                    System.out.println("Pokemon List - Sorted By Name:");
                    for(Pokemon pokemon : sortedPokemonListByType) {
                        name = pokemon.getName();
                        idString = "#" + String.format("%03d", pokemon.getId());
                        type = pokemon.getType();

                        System.out.println(idString + "-" + name + "\t\t" + "TYPE: " + type);
                    }
                    break;
                case 5:
                    if(pokedex.getPokemonArrayList().isEmpty()) {
                        System.out.println("Pokedex is empty!");
                        break;
                    }

                    System.out.println("Pokemon List:");
                    for(Pokemon pokemon : pokedex.getPokemonArrayList()) {
                        name = pokemon.getName();
                        idString = "#" + String.format("%03d", pokemon.getId());
                        type = pokemon.getType();

                        System.out.println(idString + "-" + name + "\t\t" + "TYPE: " + type);
                    }

                    String chosen;
                    Pokemon pokemon;
                    System.out.print("Enter a pokemon's name: ");
                    chosen = scanner.next().toUpperCase();

                    pokemon = pokedex.selectPokemon(chosen);
                    if(pokemon == null) {
                        System.out.println("Pokemon not found!");
                        return;
                    } else {
                        System.out.println("Pokemon Details:");
                        System.out.println("ID: " + pokemon.getId());
                        System.out.println("Name: " + pokemon.getName());
                        System.out.println("Type: " + pokemon.getType());
                        System.out.println("Description: " + pokemon.getDescription());
                    }
                    break;
                case 6:
                    PokeFile.writePokeFile(pokedex.getPokemonArrayList());
                    break;
                case 7:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 8);

        scanner.close();
    }


}
