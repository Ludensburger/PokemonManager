import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;



public class PokemonManager {
    public static void main(String[] args) {


        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));

        ArrayList<Pokemon> pokemonList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        pokemonList.add(new Pokemon("Bulbasaur", "Grass/Poison"));
        pokemonList.add(new Pokemon("Charmander", "Fire"));
        pokemonList.add(new Pokemon("Squirtle", "Water"));

        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Add Pokemon");
            System.out.println("2. Show Pokemon List");
            System.out.println("3. Sort by Name");
            System.out.println("4. Sort by Type");
            System.out.println("5. Select Pokemon");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Pokemon.addPokemon(pokemonList, scanner);
                    break;
                case 2:
                    Pokemon.showPokemonList(pokemonList);
                    break;
                case 3:
                    Pokemon.sortByName(pokemonList);
                    break;
                case 4:
                    Pokemon.sortByType(pokemonList);
                    break;
                case 5:
                    Pokemon.selectPokemon(pokemonList, scanner);
                    break;
                case 6:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 6);

        scanner.close();
    }


}
