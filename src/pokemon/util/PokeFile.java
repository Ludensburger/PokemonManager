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
        String FOLDER_PATH = WORKING_DIR + File.separator + "src" + File.separator + "dat" + File.separator;
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
        File file = getPokeFile();
        FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);

            // Iterates through all existing hashmap values
            for(Pokemon pokemon : PokemonHashMap.values()) {
                // Converts all Pokemon objects into bytes and writes each into "pokemon_data.txt"
                oos.writeObject(pokemon);
                System.out.println(pokemon.getName() + " has been registered!");
            }
            oos.flush();
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static HashMap<Integer, Pokemon> readPokeFile() throws IOException, ClassNotFoundException {
        File file = getPokeFile();
        FileInputStream fis;
        ObjectInputStream ois;

        HashMap<Integer, Pokemon> POKEMON_DATA = new HashMap<>();

        // Checks for empty file
        if(file.length() == 0) {
            System.out.println("PokeFile is empty.");
            //returns empty hashmap if file is empty
            return POKEMON_DATA;
        }

        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            while (true) {
                try {
                    // Reads through bytes in file and converts them into Pokemon objects
                    Pokemon pokemon = (Pokemon) ois.readObject();
                    // Adds each Pokemon object into hashmap
                    POKEMON_DATA.put(pokemon.getId(), pokemon);
                } catch (EOFException e) {
                     // Stops file reader when end-of-file is reached
                    break;
                }
            }
            ois.close();
        } catch (IOException e) {
            throw new IOException("Error reading PokeFile: ", e);
        }

        System.out.println("Pokedex loaded successfully!");
        return POKEMON_DATA;
    }
}
