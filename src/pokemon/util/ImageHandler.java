package pokemon.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageHandler {
    private final String IMG_FOLDER = "src" + File.separator + "img" + File.separator;
    private final String POKEMONS_FOLDER = getIMG_FOLDER() + "pokemons" + File.separator;
    private final String POKEDEX_FOLDER = getIMG_FOLDER() + "pokedex" + File.separator;
    private final String POKEMON_TYPES_FOLDER = getIMG_FOLDER() + "pokemontypes" + File.separator;

    public ImageHandler() {}

    private ImageIcon loadImage(String folderPath, String fileName, int width, int height, boolean isGIF) {
        String imagePath = folderPath + fileName;
        ImageIcon image = null;

        try {
            if(!isGIF) {
                Image rawImage = ImageIO.read(new File(imagePath));
                Image scaledImage = rawImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                image = new ImageIcon(scaledImage);
            } else {
                image = new ImageIcon(imagePath);
            }
        } catch (IOException e) {
           throw new RuntimeException();
        }

        return image;
    }

    public File getPokemonImageFile(int pokemonId) {
        return new File(getPOKEMONS_FOLDER() + String.format("%03d", pokemonId) + ".png");
    }

    public ImageIcon getPokemonImage(int width, int height, int pokemonId, boolean isGIF) {
        return loadImage(getPOKEMONS_FOLDER(), String.format("%03d", pokemonId) + ".png", width, height, isGIF);
    }

    public ImageIcon getPokedexImage(int width, int height, String imageName, boolean isGIF) {
        return loadImage(getPOKEDEX_FOLDER(), imageName, width, height, isGIF);
    }

    public ImageIcon getScaledPokedexIcon(int width, int height, String folderName, String fileName, boolean isGIF) {
        return loadImage(getPOKEDEX_FOLDER() + folderName + File.separator, fileName, width, height, isGIF);
    }

    public ImageIcon getPokemonTypeIcon(int width, int height, String fileName, boolean isGIF) {
        return loadImage(getPOKEMON_TYPES_FOLDER(), fileName, width, height, isGIF);
    }

    public String getIMG_FOLDER() {
        return IMG_FOLDER;
    }

    public String getPOKEMONS_FOLDER() {
        return POKEMONS_FOLDER;
    }

    public String getPOKEDEX_FOLDER() {
        return POKEDEX_FOLDER;
    }

    public String getPOKEMON_TYPES_FOLDER() {
        return POKEMON_TYPES_FOLDER;
    }
}
