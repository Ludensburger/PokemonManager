package pokemon.util;

import java.io.*;
import java.util.HashMap;
import pokemon.Pokemon;

public class PokeFile {
    private static File pokeFile;

    /**
     *
     * @return
     * locates "pokedex_data.txt" file which is where all Pokemon data are stored as bytes
     */
    private static File createFile() {
        String WORKING_DIR = System.getProperty("user.dir");
        String FOLDER_PATH = WORKING_DIR + File.separator + "src" + File.separator + "data" + File.separator;
        String FILE_NAME = "pokedex_data.txt";
        String FILE_PATH = FOLDER_PATH + FILE_NAME;

        return new File(FILE_PATH);
    }
    public static File getPokeFile() {
        if (pokeFile == null) {
            pokeFile = createFile();
        }
        return pokeFile;
    }

    /**
     *
     * @param PokemonHashMap
     *          Retrieves current running hashmap from Pokedex for file writing
     * @throws IOException
     */
    public static void writePokeFile(HashMap<Integer, Pokemon> PokemonHashMap) throws IOException {
        File FILE = getPokeFile();
        FileOutputStream FOS;
        ObjectOutputStream OOS;

        try {
            FOS = new FileOutputStream(FILE);
            OOS = new ObjectOutputStream(FOS);

            // Iterates through all existing hashmap values
            for(Pokemon pokemon : PokemonHashMap.values()) {
                // Converts all Pokemon objects into bytes and writes each into "pokemon_data.txt"
                OOS.writeObject(pokemon);
                System.out.println(pokemon.getName() + " has been registered!");
            }
            OOS.flush();
            OOS.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static HashMap<Integer, Pokemon> readPokeFile() throws IOException, ClassNotFoundException {
        File FILE = getPokeFile();
        FileInputStream FIS;
        ObjectInputStream OIS;

        HashMap<Integer, Pokemon> POKEMON_DATA = new HashMap<>();

        // Checks for empty file
        if(FILE.length() == 0) {
            System.out.println("PokeFile is empty.");
            //returns empty hashmap if file is empty
            return POKEMON_DATA;
        }

        try {
            FIS = new FileInputStream(FILE);
            OIS = new ObjectInputStream(FIS);
            while (true) {
                try {
                    // Reads through bytes in file and converts them into Pokemon objects
                    Pokemon pokemon = (Pokemon) OIS.readObject();
                    // Adds each Pokemon object into hashmap
                    POKEMON_DATA.put(pokemon.getId(), pokemon);
                } catch (EOFException e) {
                     // Stops file reader when end-of-file is reached
                    break;
                }
            }
            OIS.close();
        } catch (IOException e) {
            throw new IOException("Error reading PokeFile: ", e);
        }

        System.out.println("Pokedex loaded successfully!");
        return POKEMON_DATA;
    }
}
