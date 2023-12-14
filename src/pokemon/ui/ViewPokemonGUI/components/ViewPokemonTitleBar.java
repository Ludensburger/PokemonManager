package pokemon.ui.ViewPokemonGUI.components;

import pokemon.ui.components.TitleBar.ButtonModel;
import pokemon.ui.components.TitleBar.SetDraggableFunction;
import pokemon.ui.UIRunner;
import pokemon.ui.ViewPokemonGUI.design.ViewPokemonGUIDesign;
import pokemon.ui.components.TitleBar.TitleBarModel;
import pokemon.util.AudioHandler;
import pokemon.util.FontHandler;
import pokemon.util.ImageHandler;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ViewPokemonTitleBar extends TitleBarModel {
    private AudioHandler viewPokemonMusic;
    private JFrame viewPokemonFrame;

    public ViewPokemonTitleBar(JFrame viewPokemonFrame, AudioHandler viewPokemonMusic) throws IOException, FontFormatException {
        super(viewPokemonFrame);

        setViewPokemonFrame(viewPokemonFrame);
        setViewPokemonMusic(viewPokemonMusic);

        super.getButtonContainer().setPreferredSize(new Dimension(100,50));
    }


    public void closeViewPokemonFrame() throws LineUnavailableException, IOException {
        UIRunner.getInstance().closeViewPokemonGUI();
        UIRunner.getInstance().openPokedexGUI();
    }

    public AudioHandler getViewPokemonMusic() {
        return viewPokemonMusic;
    }

    public void setViewPokemonMusic(AudioHandler viewPokemonMusic) {
        this.viewPokemonMusic = viewPokemonMusic;
    }

    public JFrame getViewPokemonFrame() {
        return viewPokemonFrame;
    }

    public void setViewPokemonFrame(JFrame viewPokemonFrame) {
        this.viewPokemonFrame = viewPokemonFrame;
    }
}
