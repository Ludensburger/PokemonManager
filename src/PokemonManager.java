import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class PokemonManager {
    public static void main(String[] args) {
        ArrayList<Pokemon> pokemonList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Add Pokemon");
            System.out.println("2. Show Pokemon List");
            System.out.println("3. Sort by Name");
            System.out.println("4. Sort by Type");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addPokemon(pokemonList, scanner);
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
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 5);

        scanner.close();
    }

    private static void addPokemon(ArrayList<Pokemon> pokemonList, Scanner scanner) {
        System.out.print("Enter Pokemon name: ");
        String name = scanner.next();
        System.out.print("Enter Pokemon type: ");
        String type = scanner.next();

        Pokemon newPokemon = new Pokemon(name, type);
        pokemonList.add(newPokemon);

        System.out.println("Pokemon added successfully!");
    }


}
