import java.io.IOException;
import java.util.Scanner;

public class PokemonManager {
    public static void main(String[] args) throws IOException {
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
        Scanner scanner = new Scanner(System.in);
        Pokedex.updatePokedex();


        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Add Pokemon");
            System.out.println("2. Show Pokemon List");
            System.out.println("3. Sort by Name");
            System.out.println("4. Sort by Type");
            System.out.println("5. Select Pokemon");
            System.out.println("6. Update Pokedex");
            System.out.println("7. Save Pokedex");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Pokedex.addPokemon();
                    break;
                case 2:
                    Pokedex.showPokemonList();
                    break;
                case 3:
                    Pokedex.sortByName();
                    break;
                case 4:
                    Pokedex.sortByType();
                    break;
                case 5:
                    Pokedex.selectPokemon();
                    break;
                case 6:
                    Pokedex.updatePokedex();
                    break;
                case 7:
                    Pokedex.savePokedex();
                    break;
                case 8:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 8);

        scanner.close();
    }


}
