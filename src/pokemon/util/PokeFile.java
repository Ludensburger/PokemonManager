package pokemon.util;

import java.io.*;
import java.util.HashMap;
import pokemon.Pokemon;

public class PokeFile {
    private static File pokeFile;
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
    public static void writePokeFile(HashMap<Integer, Pokemon> PokemonHashMap) throws IOException {
        File file = getPokeFile();
        FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);

            for(Pokemon pokemon : PokemonHashMap.values()) {
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

        //Check for empty file
        if(file.length() == 0) {
            System.out.println("PokeFile is empty.");
            return POKEMON_DATA;
        }

        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            while (true) {
                try {
                    Pokemon pokemon = (Pokemon) ois.readObject();
                    POKEMON_DATA.put(pokemon.getId(), pokemon);
                } catch (EOFException e) {
                    //breaks loop when EOF has been reached
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
