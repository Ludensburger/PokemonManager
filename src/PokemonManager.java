import pokemon.Pokedex;

import java.io.File;
import java.io.IOException;
import java.util.*;

import pokemon.*;
import pokemon.util.PokeFile;

public class PokemonManager {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
        Scanner scanner = new Scanner(System.in);
        Pokedex pokedex = new Pokedex();
        int choice;
        pokedex.startPokedex();

        String name, desc, bool, type, idString;
        PokemonType primaryType, secondaryType;

        do {
            System.out.println("Menu:");
            System.out.println("1. Add Pokemon");
            System.out.println("2. Show Pokemon List");
            System.out.println("3. Remove Pokemon");
            System.out.println("4. Update Pokemon");
            System.out.println("5. View Pokemon");
            System.out.println("6. Update Pokedex");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name of pokemon: ");
                    name = scanner.nextLine().toUpperCase();

                    System.out.print("Describe your pokemon: ");
                    desc = scanner.nextLine().toUpperCase();

                    System.out.print("Does your pokemon have two types? [Y/N]: ");
                    bool = scanner.nextLine();

                    PokemonType pickPrimaryType = PokemonType.pickType();

                    int id = pokedex.getPokemonHashMap().size() + 1;

                    Pokemon.PokemonBuilder newPokemon = new Pokemon.PokemonBuilder(name, id)
                            .setPrimaryType(pickPrimaryType)
                            .setDescription(desc)
                            .setSecondaryType(null);

                    if(Objects.equals(bool, "y") || Objects.equals(bool, "Y")) {
                        PokemonType pickSecondaryType;
                        do {
                            pickSecondaryType = PokemonType.pickType();
                            if(Objects.equals(pickPrimaryType, pickSecondaryType)) {
                                System.out.println("A pokemon can't have two of the same type!");
                            }
                        } while(Objects.equals(pickPrimaryType, pickSecondaryType));
                        newPokemon.setSecondaryType(pickSecondaryType);
                    }
                    pokedex.addPokemon(newPokemon.build());
                    break;
                case 2:
                    if(pokedex.getPokemonHashMap().isEmpty()) {
                        System.out.println("Pokedex is empty!");
                        break;
                    }
                    showPokemonList(pokedex.getPokemonHashMap());
                    break;
                case 3:
                    if(pokedex.getPokemonHashMap().isEmpty()) {
                        System.out.println("Pokedex is empty!");
                        break;
                    }
                    showPokemonList(pokedex.getPokemonHashMap());

                    int remove;
                    String removePokemonName;
                    System.out.print("Enter a pokemon's id: ");
                    remove = scanner.nextInt();

                    removePokemonName = pokedex.selectPokemon(remove).getName();
                    pokedex.removePokemon(remove);

                    System.out.println(removePokemonName + " has been deleted!");
                    break;
                case 4:
                    if(pokedex.getPokemonHashMap().isEmpty()) {
                        System.out.println("Pokedex is empty!");
                        break;
                    }
                    showPokemonList(pokedex.getPokemonHashMap());

                    System.out.println("Pick a pokemon's ID to modify: ");
                    int toModifyId = scanner.nextInt();
                    Pokemon pokemonToModify = pokedex.selectPokemon(toModifyId);

                    System.out.println("What do you want to modify?");
                    System.out.println("1. Description");
                    System.out.println("2. Primary Type");
                    System.out.println("3. Secondary Type");
                    int modifyChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (modifyChoice == 1) {
                        System.out.println("Enter new description: ");
                        String newDescription = scanner.nextLine();
                        Pokemon.PokemonBuilder pokemonBuilder = new Pokemon.PokemonBuilder(pokemonToModify)
                                .setDescription(newDescription)
                                .setPrimaryType(pokemonToModify.getPrimaryType())
                                .setSecondaryType(pokemonToModify.getSecondaryType());
                        pokedex.updatePokemon(toModifyId, pokemonBuilder.build());
                    } else if (modifyChoice == 2) {
                        System.out.println("Enter new primary type: ");
                        PokemonType newPrimaryType = PokemonType.pickType();
                        Pokemon.PokemonBuilder pokemonBuilder = new Pokemon.PokemonBuilder(pokemonToModify)
                                .setDescription(pokemonToModify.getDescription())
                                .setPrimaryType(newPrimaryType)
                                .setSecondaryType(pokemonToModify.getSecondaryType());
                        pokedex.updatePokemon(toModifyId, pokemonBuilder.build());
                    } else if (modifyChoice == 3) {
                        System.out.println("Enter new secondary type: ");
                        PokemonType newSecondaryType = PokemonType.pickType();
                        Pokemon.PokemonBuilder pokemonBuilder = new Pokemon.PokemonBuilder(pokemonToModify)
                                .setDescription(pokemonToModify.getDescription())
                                .setPrimaryType(pokemonToModify.getPrimaryType())
                                .setSecondaryType(newSecondaryType);
                        pokedex.updatePokemon(toModifyId, pokemonBuilder.build());
                    } else {
                        System.out.println("Invalid choice!");
                    }
                    break;
                case 5:
                    if(pokedex.getPokemonHashMap().isEmpty()) {
                        System.out.println("Pokedex is empty!");
                        break;
                    }
                    showPokemonList(pokedex.getPokemonHashMap());

                    int view;
                    Pokemon viewPokemon;
                    System.out.print("Enter a pokemon's id: ");
                    view = scanner.nextInt();

                    viewPokemon = pokedex.selectPokemon(view);
                    if(viewPokemon == null) {
                        System.out.println("Pokemon not found!");
                        return;
                    } else {
                        System.out.println("Pokemon Details:");
                        System.out.println("ID: " + viewPokemon.getId());
                        System.out.println("Name: " + viewPokemon.getName());
                        System.out.println("Primary Type: " + viewPokemon.getPrimaryType());
                        System.out.println("Secondary Type: " + viewPokemon.getSecondaryType());
                        System.out.println("Description: " + viewPokemon.getDescription());
                    }
                    break;
                case 6:
                    PokeFile.writePokeFile(pokedex.getPokemonHashMap());
                    break;
                case 7:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 7);

        scanner.close();
    }

    public static void showPokemonList(HashMap<Integer, Pokemon> pokedex) {
        System.out.println("Pokemon List:");
        for(Pokemon pokemon : pokedex.values()) {
            String name = pokemon.getName();
            String idString = "#" + String.format("%03d", pokemon.getId());
            PokemonType primaryType = pokemon.getPrimaryType();
            String type;

            if(pokemon.getSecondaryType() != null) {
                PokemonType secondaryType = pokemon.getSecondaryType();
                type = primaryType + "/" + secondaryType;
            } else {
                type = String.valueOf(pokemon.getPrimaryType());
            }

            System.out.println(idString + "-" + name + "\t\t" + "TYPE: " + type);
        }
    }
}
