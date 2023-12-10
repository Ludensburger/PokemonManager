package pokemon.ui.PokedexGUI.actions;

import pokemon.Pokemon;
import pokemon.ui.viewPokemonGUI.ViewPokemonGUI;
import pokemon.util.AudioHandler;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class HandleKeys {

    private PanelPainter panelPainter;

    private int MAX_POKEMON;

    private int MAX_POKEMON_PANELS;

    private JFrame mainMenuFrame;

    private AudioHandler mainMenuMusic;

    public HandleKeys(PanelPainter panelPainter, JFrame frame, AudioHandler mainMenuMusic) {
        setPanelPainter(panelPainter);
        setMAX_POKEMON(panelPainter.getPokedex().getPokemonHashMap().size());
        setMAX_POKEMON_PANELS(panelPainter.getPokemonPanels().size());
        setMainMenuFrame(frame);
        setMainMenuMusic(mainMenuMusic);
    }

    public KeyAdapter addHandleKeys() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    getPanelPainter().getNavigatorInstance().moveUp();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    getPanelPainter().getNavigatorInstance().moveDown(getMAX_POKEMON(), getMAX_POKEMON_PANELS());
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Pokemon selectedPokemon = getPanelPainter().getEnlargedPokemonPanel().getPokemon();
                    try {
                        ViewPokemonGUI viewPokemonGUI = new ViewPokemonGUI(selectedPokemon);
                        viewPokemonGUI.setVisible(true);
                    } catch (IOException | FontFormatException | UnsupportedAudioFileException |
                             LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    }

                    getMainMenuFrame().setVisible(false);
                    getMainMenuMusic().pause();
                    getMainMenuFrame().dispose();
                }

                try {
                    getPanelPainter().paintPanels();
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                    throw new RuntimeException(ex);
                }



            }
        };
    }

    public PanelPainter getPanelPainter() {
        return panelPainter;
    }

    public void setPanelPainter(PanelPainter panelPainter) {
        this.panelPainter = panelPainter;
    }

    public int getMAX_POKEMON() {
        return MAX_POKEMON;
    }

    public void setMAX_POKEMON(int MAX_POKEMON) {
        this.MAX_POKEMON = MAX_POKEMON;
    }

    public int getMAX_POKEMON_PANELS() {
        return MAX_POKEMON_PANELS;
    }

    public void setMAX_POKEMON_PANELS(int MAX_POKEMON_PANELS) {
        this.MAX_POKEMON_PANELS = MAX_POKEMON_PANELS;
    }

    public JFrame getMainMenuFrame() {
        return mainMenuFrame;
    }

    public void setMainMenuFrame(JFrame mainMenuFrame) {
        this.mainMenuFrame = mainMenuFrame;
    }

    public AudioHandler getMainMenuMusic() {
        return mainMenuMusic;
    }

    public void setMainMenuMusic(AudioHandler mainMenuMusic) {
        this.mainMenuMusic = mainMenuMusic;
    }

}
