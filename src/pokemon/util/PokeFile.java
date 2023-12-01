package pokemon.util;

import java.io.*;
import java.util.ArrayList;
import pokemon.Pokemon;

public class PokeFile {
    private static File pokeFile;
    private static File createFile() {
        String WORKING_DIR = System.getProperty("user.dir");
        String FOLDER_PATH = WORKING_DIR + File.separator + "src" + File.separator + "dat" + File.separator;
        String FILE_NAME = "pokemon_data.txt";
        String FILE_PATH = FOLDER_PATH + FILE_NAME;

        return new File(FILE_PATH);
    }
    public static File getPokeFile() {
        if (pokeFile == null) {
            pokeFile = createFile();
        }
        return pokeFile;
    }

    public static void writePokeFile(ArrayList<Pokemon> pokemonArrayList) throws FileNotFoundException {
        File file = getPokeFile();
        FileWriter fw;
        BufferedWriter bw;

        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);

            for(Pokemon pokemon : pokemonArrayList) {
                String parsePokemonObjectToString =
                        "\nid:" + pokemon.getId() +
                        "\nname:" + pokemon.getName() +
                        "\ntype:" + pokemon.getType() +
                        "\ndesc:" + pokemon.getDescription();

                bw.write(parsePokemonObjectToString);
                System.out.println(pokemon.getName() + " has been registered!");
            }
            bw.flush();
            bw.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static ArrayList<Pokemon> readPokeFile() throws FileNotFoundException {
        File file = getPokeFile();
        FileReader fr;
        BufferedReader br;
        String NAME = null, TYPE = null, DESC = null, ID = null;
        String CURRENT_LINE;
        boolean isNewPokemon = false;
        ArrayList<Pokemon> POKEMON_DATA = new ArrayList<>();

        try {
            fr  = new FileReader(file);
            br = new BufferedReader(fr);

            while((CURRENT_LINE = br.readLine()) != null) {

                if(CURRENT_LINE.contains("id:")) {
                    ID = CURRENT_LINE.substring(3).trim();
                }
                if(CURRENT_LINE.contains("name:")) {
                    NAME = CURRENT_LINE.substring(5).trim();
                }
                if(CURRENT_LINE.contains("type:")) {
                    TYPE = CURRENT_LINE.substring(5).trim();
                }
                if(CURRENT_LINE.contains("desc:")) {
                    DESC = CURRENT_LINE.substring(5).trim();
                }

                if(DESC != null && ID != null) {
                    int ID_INT = Integer.parseInt(ID);
                    Pokemon pokemon = new Pokemon.PokemonBuilder(NAME, ID_INT, DESC, TYPE).build();
                    POKEMON_DATA.add(pokemon);
                    isNewPokemon = true;
                }

                if(isNewPokemon) {
                    ID = null;
                    NAME = null;
                    TYPE = null;
                    DESC = null;
                    isNewPokemon = false;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return POKEMON_DATA;
    }
}
