package pokemon.ui;

import java.awt.*;
import java.io.IOException;

import pokemon.*;
import pokemon.ui.PokedexGUI.PokedexGUI;
import pokemon.ui.viewPokemonGUI.ViewPokemonGUI;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *  Used for running JFrames
 */
public class UIRunner {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        startPokedexGUI();
    }

    public static void startPokedexGUI() throws UnsupportedAudioFileException, LineUnavailableException, IOException, FontFormatException, ClassNotFoundException, CloneNotSupportedException {
        Pokedex pokedex = new Pokedex();
        pokedex.startPokedex();
        PokedexGUI frame = new PokedexGUI(pokedex);
    }
}
