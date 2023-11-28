import java.io.*;
import java.util.*;
/**
 *  Pokedex class that handles various tasks such as:
 *      1. Initializing ArrayList of Pokemon called 'PokemonList'
 *      2. Adding objects to PokemonList through addPokemon();
 *      3. Displaying PokemonList through showPokemonList();
 *      4. Sort pokemonList through sortByName() and sortByType()
 *      5. Write current PokemonList data to pokemon_data.txt file
 *         through savePokedex()
 *      6. Read data from pokemon_data.txt file to PokemonList through
 *          updatePokedex()
 *      7. Select Pokemon from PokemonList to display more of their details
 *         through selectPokemon()
 *
 *
 *   NOTE:
 *      Run savePokedex() to store files locally.
 *
 */
public class Pokedex{
    static List<Pokemon> PokemonList = new ArrayList<Pokemon>();
    static Scanner scanner = new Scanner(System.in);

    static void addPokemon() {
        String yn;
        String name;
        PokemonType singleType;
        PokemonType dualType;
        String type;
        String description;

        Pokemon newPokemon;

        System.out.print("Enter name of pokemon: ");
        name = scanner.nextLine();

        System.out.print("Describe your pokemon: ");
        description = scanner.nextLine();

        System.out.print("Does your pokemon have two types? [Y/N]: ");
        yn = scanner.nextLine();

        singleType = PokemonType.pickType();

        if (Objects.equals(yn, "Y") || Objects.equals(yn, "y")) {
            dualType = PokemonType.pickType();
            type = singleType + "/" + dualType;
            newPokemon = new Pokemon(name, PokemonList.size() + 1, description, type, true);
        } else {
            type = String.valueOf(singleType);
            newPokemon = new Pokemon(name, PokemonList.size() + 1, description, type, false);
        }

        PokemonList.add(newPokemon);
        System.out.println("Pokemon added successfully!");
    }

    static void showPokemonList() {
        if (PokemonList.isEmpty()) {
            System.out.println("No Pokemon in the list.");
        } else {
            System.out.println("Pokemon List:");
            for (Pokemon pokemon : PokemonList) {
                System.out.println("#" + pokemon.getId() + " - " + pokemon.getName() + "\t\t" + "Type: " + pokemon.getType());
            }
        }
    }

    static void sortByName() {
        PokemonList.sort(Comparator.comparing(Pokemon::getName));
        System.out.println("Pokemon list sorted by name.");
    }

    static void sortByType() {
        PokemonList.sort(Comparator.comparing(Pokemon::getType));
        System.out.println("Pokemon list sorted by type.");
    }

    static void savePokedex() throws IOException {
        String folderPath = "src" + File.separator + "dat" + File.separator;
        String fileName = "pokemon_data.txt";
        String filePath = folderPath + fileName;

        File pokeFile = new File(filePath);
        try {
           BufferedWriter br = new BufferedWriter(new FileWriter(pokeFile));
            for(Pokemon pokemon : PokemonList) {
                String pokeObject = "\nid:" + pokemon.getId() +
                        "\nname:" + pokemon.getName() +
                        "\ntype:" + pokemon.getType() +
                        "\ndesc:" + pokemon.getDescription();

                br.write(pokeObject);
            }
            br.flush();
            br.close();
            System.out.println("Pokedex saved successfully!");

        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    static void updatePokedex() throws IOException {
        String folderPath = "src" + File.separator + "dat" + File.separator;
        String fileName = "pokemon_data.txt";
        String filePath = folderPath + fileName;

        File pokeFile = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(pokeFile));
            String line;

            String id = null;
            String name = null;
            String type = null;
            String description = null;
            Set<String> pokemonChecked = new HashSet<String>();

            /*
                reads each line of file until it reaches EOF
                keys and values are parsed and strings
                if-statements find keys, and trims them off to get values
                once all keys are met, create new object to add to PokemonList
             */

            while((line = br.readLine()) != null) {
                if(line.contains("id:")) {
                    id = line.substring(3).trim();
                }
                if (line.contains("name:")) {
                    name = line.substring(5).trim();
                }
                if (line.contains("type:")) {
                    type = line.substring(5).trim();
                    System.out.println(type);
                }
                if (line.contains("desc:")) {
                    description = line.substring(5).trim();
                }

                if(id != null && name != null && type != null && description != null) {
                    Pokemon storedPokemon;
                    boolean isIdTaken = false;
                    int idNo = Integer.parseInt(id);

                    if(type.contains("/")) {
                        storedPokemon = new Pokemon(name, idNo, description, type, true);
                    } else {
                        storedPokemon = new Pokemon(name, idNo, description, type, false);
                    }

                    System.out.println(storedPokemon.getName());

                    for(Pokemon pokemon : PokemonList) {
                        if (pokemon.getId() == idNo) {
                            isIdTaken = true;
                            break;
                        }
                    }
                    if(!isIdTaken) {
                        PokemonList.add(storedPokemon);

                    }


                    id = null;
                    name = null;
                    type = null;
                    description = null;
                }
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    static void selectPokemon() {
        System.out.print("Enter the name of the Pokemon: ");
        String selectedName = scanner.next();

        Pokemon selectedPokemon;
        for(Pokemon pokemon : PokemonList) {
            if(pokemon.getName().equalsIgnoreCase(selectedName)) {
                displayPokemonDetails(pokemon);
                return;
            }
        }

        System.out.println("Pokemon not found.");

    }

    private static void displayPokemonDetails(Pokemon pokemon) {
        System.out.println("Pokemon Details:");
        System.out.println("ID: " + pokemon.getId());
        System.out.println("Name: " + pokemon.getName());
        System.out.println("Type: " + pokemon.getType());
        System.out.println("Description: " + pokemon.getDescription());
    }

}
