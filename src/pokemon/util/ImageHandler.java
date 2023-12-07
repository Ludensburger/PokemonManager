package pokemon.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHandler {
    public ImageHandler() {}
    public ImageIcon getPokemonImage(int width, int height, int pokemonId) {
        String FOLDER_PATH = "src" + File.separator + "img" + File.separator + "pokemons" + File.separator;
        String IMAGE_PATH = FOLDER_PATH + String.format("%03d", pokemonId) + ".png";
        ImageIcon IMAGE = null;

        try {
            Image RAW_IMAGE = ImageIO.read(new File(IMAGE_PATH));
            Image SCALED_IMAGE = RAW_IMAGE.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            IMAGE = new ImageIcon(SCALED_IMAGE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return IMAGE;
    }

    public ImageIcon getPokedexImage(String imageName) {
        String FOLDER_PATH = "src" + File.separator + "img" + File.separator + "pokedex" + File.separator;
        String IMAGE_PATH = FOLDER_PATH + imageName;

//        try {
//            Image RAW_IMAGE = ImageIO.read(new File(IMAGE_PATH));
//            Image SCALED_IMAGE = RAW_IMAGE.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//            IMAGE = new ImageIcon(SCALED_IMAGE);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        return new ImageIcon(IMAGE_PATH);
    }

    public ImageIcon getGIF(int width, int height, String folderName, String fileName) {
        String FOLDER_PATH = "src" + File.separator + "img" + File.separator + "pokedex" + File.separator + folderName + File.separator;
        String IMAGE_PATH = FOLDER_PATH + fileName;

//        try {
//            Image RAW_IMAGE = ImageIO.read(new File(IMAGE_PATH));
//            Image SCALED_IMAGE = RAW_IMAGE.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//            IMAGE = new ImageIcon(SCALED_IMAGE);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        return new ImageIcon(IMAGE_PATH);
    }
}
