package pokemon.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageHandler {
    private static final String IMG_FOLDER = "src" + File.separator + "img" + File.separator;
    private static final String POKEMONS_FOLDER = IMG_FOLDER + "pokemons" + File.separator;
    private static final String POKEDEX_FOLDER = IMG_FOLDER + "pokedex" + File.separator;
    private static final String POKEMON_TYPES_FOLDER = IMG_FOLDER + "pokemontypes" + File.separator;

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

    public ImageIcon getPokemonImage(int width, int height, int pokemonId, boolean isGIF) {
        return loadImage(POKEMONS_FOLDER, String.format("%03d", pokemonId) + ".png", width, height, isGIF);
    }

    public ImageIcon getPokedexImage(int width, int height, String imageName, boolean isGIF) {
        return loadImage(POKEDEX_FOLDER, imageName, width, height, isGIF);
    }

    public ImageIcon getScaledPokedexIcon(int width, int height, String folderName, String fileName, boolean isGIF) {
        return loadImage(POKEDEX_FOLDER + folderName + File.separator, fileName, width, height, isGIF);
    }

    public ImageIcon getPokemonTypeIcon(int width, int height, String fileName, boolean isGIF) {
        return loadImage(POKEMON_TYPES_FOLDER, fileName, width, height, isGIF);
    }
}
