package pokemon.ui.PokedexGUI.actions;

import pokemon.Pokedex;
import pokemon.Pokemon;
import pokemon.ui.PokedexGUI.components.PokemonPanel;
import pokemon.ui.UIRunner;
import pokemon.util.AudioHandler;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public class ResponsiveSearch {

    private PanelPainter panelPainter;

    private final PanelNavigator panelNavigator;

    public ResponsiveSearch(PanelPainter panelPainter) {
        this.panelPainter = panelPainter;
        this.panelNavigator = panelPainter.getNavigatorInstance();
    };

    public PanelPainter getPanelPainter() {
        return panelPainter;
    }

    public void setPanelPainter(PanelPainter panelPainter) {
        this.panelPainter = panelPainter;
    }

    public PanelNavigator getPanelNavigator() {
        return panelNavigator;
    }

    public KeyAdapter addResponsiveSearch(JTextField textField, JFrame mainMenuFrame, AudioHandler mainMenuMusic) {
        return new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                try {
                    handleResponsiveSearch(textField);
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                    throw new RuntimeException(ex);
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Pokemon selectedPokemon = getPanelPainter().getEnlargedPokemonPanel().getPokemon();
                    System.out.println(selectedPokemon.getName());
                    try {
                        UIRunner.getInstance().closePokedexGUI();
                        UIRunner.getInstance().openViewPokemonGUI(selectedPokemon);

                    } catch (LineUnavailableException | IOException | UnsupportedAudioFileException |
                             FontFormatException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }


        };
    }

    private void handleResponsiveSearch(JTextField textField) throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        String activeText = textField.getText().toUpperCase();
        int CURRENT_POKEMON_SELECTED_INDEX = getPanelNavigator().getPokemonIndex();
        int TO_FIND_POKEMON_INDEX = findIndexOfPokemon(activeText);

        movePanelsToPokemon(TO_FIND_POKEMON_INDEX, CURRENT_POKEMON_SELECTED_INDEX);
        if(CURRENT_POKEMON_SELECTED_INDEX != TO_FIND_POKEMON_INDEX) {
            getPanelPainter().paintPanels();
        }
    }

    private void movePanelsToPokemon(int targetPanelIndex, int currentPanelIndex) {
        Pokedex pokedex = getPanelPainter().getPokedex();
        ArrayList<PokemonPanel> pokemonPanels = getPanelPainter().getPokemonPanels();

        int MAX_PANELS = pokemonPanels.size();
        int MAX_POKEMON = pokedex.getPokemonHashMap().size();

        while (currentPanelIndex != targetPanelIndex) {
            if (currentPanelIndex > targetPanelIndex) {
                currentPanelIndex--;
                getPanelNavigator().moveUp();
            } else {
                currentPanelIndex++;
                getPanelNavigator().moveDown(MAX_POKEMON, MAX_PANELS);
            }
        }

    }

    private int findIndexOfPokemon(String pokemonName) {
        Pokedex pokedex = getPanelPainter().getPokedex();
        for(Pokemon pokemon : pokedex.getPokemonHashMap().values()) {
            if(pokemon.getName().contains(pokemonName)) {
                return pokemon.getId();
            }
        }

        return 1;
    }
}
