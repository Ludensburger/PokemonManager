package pokemon.ui;

import java.awt.*;
import java.io.IOException;

import pokemon.*;
import pokemon.ui.PokedexGUI.PokedexGUI;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *  Used for running JFrames
 */
public class UIRunner {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        Pokedex pokedex = new Pokedex();
        PokedexGUI frame = new PokedexGUI();
    }
}
