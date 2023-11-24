import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Pokemon {
    private static int nextId = 1;
    private int id;
    private String name;
    private String type;
    private int health;
    private int experienceLevel;
    private ArrayList<String> attacks;

    public Pokemon(String name, String type) {
        this.id = nextId++;
        this.name = name;
        this.type = type;
        this.health = 100;
        this.experienceLevel = 0;
        this.attacks = new ArrayList<>();
        this.attacks.add("Tackle");
        this.attacks.add("Growl");
    }


    public String getId() {
        return String.format("%04d", id);
    }
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    public ArrayList<String> getAttacks() {
        return attacks;
    }

    public void addAttack(String attack) {
        attacks.add(attack);
    }

    static void addPokemon(ArrayList<Pokemon> pokemonList, Scanner scanner) {
        System.out.print("Enter Pokemon name: ");
        String name = scanner.next();
        System.out.print("Enter Pokemon type: ");
        String type = scanner.next();

        Pokemon newPokemon = new Pokemon(name, type);
        pokemonList.add(newPokemon);

        System.out.println("Pokemon added successfully!");
    }

    static void showPokemonList(ArrayList<Pokemon> pokemonList) {
        if (pokemonList.isEmpty()) {
            System.out.println("No Pokemon in the list.");
        } else {
            System.out.println("Pokemon List:");
            for (Pokemon pokemon : pokemonList) {
                System.out.println("#" + pokemon.getId() + " - " + pokemon.getName() + "\t Type: " + pokemon.getType());
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

    static void selectPokemon(ArrayList<Pokemon> pokemonList, Scanner scanner) {
        System.out.print("Enter the name of the Pokemon: ");
        String selectedName = scanner.next();

        Pokemon selectedPokemon = findPokemonByName(pokemonList, selectedName);

        if (selectedPokemon != null) {
            displayPokemonDetails(selectedPokemon);
        } else {
            System.out.println("Pokemon not found.");
        }
    }

    private static Pokemon findPokemonByName(ArrayList<Pokemon> pokemonList, String name) {
        for (Pokemon pokemon : pokemonList) {
            if (pokemon.getName().equalsIgnoreCase(name)) {
                return pokemon;
            }
        }
        return null;
    }

    private static void displayPokemonDetails(Pokemon pokemon) {
        System.out.println("Pokemon Details:");
        System.out.println("ID: " + pokemon.getId());
        System.out.println("Name: " + pokemon.getName());
        System.out.println("Type: " + pokemon.getType());
        System.out.println("Health: " + pokemon.getHealth() + "HP");
        System.out.println("Experience Level: " + pokemon.getExperienceLevel() + "/100");
        System.out.println("Attacks: " + String.join(", ", pokemon.getAttacks()));

        try {
            String description = loadDescriptionFromFile(pokemon.getName());
            System.out.println("Description: " + description);
        } catch (IOException e) {
            System.out.println("Error loading description: " + e.getMessage());
        }
    }

    private static String loadDescriptionFromFile(String pokemonName) throws IOException {
        String folderPath = "C:\\Users\\hp\\Desktop\\Ryu Files\\Codes\\Java\\PokemonManager\\src\\pokemon_descriptions";  // Assuming the folder is directly under the 'src' directory
        String fileName = pokemonName + ".txt";
        Path filePath = Paths.get(folderPath, fileName);

        System.out.println("File Path: " + filePath);

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new IOException("Error loading description: " + filePath, e);
        }

        return content.toString();
    }


}