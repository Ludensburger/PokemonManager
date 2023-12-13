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
    private JButton backButton;
    private JFrame viewPokemonFrame;

    public ViewPokemonTitleBar(JFrame viewPokemonFrame, AudioHandler viewPokemonMusic) throws IOException, FontFormatException {
        super(viewPokemonFrame);

        setViewPokemonFrame(viewPokemonFrame);
        setViewPokemonMusic(viewPokemonMusic);
        setBackButton(new JButton());

        super.getButtonContainer().setPreferredSize(new Dimension(150,50));
        super.getButtonContainer().add(getBackButton(), BorderLayout.WEST);
    }

    public JButton getBackButton() {
        return backButton;
    }
    public void setBackButton(JButton backButton) throws IOException, FontFormatException {
        ButtonModel.ButtonUtil.setupButton(backButton,
                "<",
                e -> {
                    try {
                        closeViewPokemonFrame();
                    } catch (LineUnavailableException | IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        );
        this.backButton = backButton;
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
