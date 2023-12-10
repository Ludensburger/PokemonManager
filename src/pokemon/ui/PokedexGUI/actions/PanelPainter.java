package pokemon.ui.PokedexGUI.actions;

import pokemon.Pokedex;
import pokemon.Pokemon;
import pokemon.ui.PokedexGUI.components.EnlargedPokemonPanel;
import pokemon.ui.PokedexGUI.components.PokemonPanel;
import pokemon.ui.PokedexGUI.design.MenuGUIDesign;
import pokemon.util.AudioHandler;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class PanelPainter implements MenuGUIDesign {

    private static PanelPainter instance;

    private ArrayList<PokemonPanel> pokemonPanels;

    private EnlargedPokemonPanel enlargedPokemonPanel;

    private PanelNavigator navigatorInstance;

    private Pokedex pokedex;

    private PanelPainter() {}

    public static PanelPainter getInstance() {
        if (instance == null) {
            instance = new PanelPainter();
        }
        return instance;
    }

    public ArrayList<PokemonPanel> getPokemonPanels() {
        return pokemonPanels;
    }

    public void setPokemonPanels(ArrayList<PokemonPanel> pokemonPanels) {
        this.pokemonPanels = pokemonPanels;
    }

    public EnlargedPokemonPanel getEnlargedPokemonPanel() {
        return enlargedPokemonPanel;
    }

    public void setEnlargedPokemonPanel(EnlargedPokemonPanel enlargedPokemonPanel) {
        this.enlargedPokemonPanel = enlargedPokemonPanel;
    }
    public PanelNavigator getNavigatorInstance() {
        return navigatorInstance;
    }

    public void setNavigatorInstance(PanelNavigator navigatorInstance) {
        this.navigatorInstance = navigatorInstance;
    }

    public Pokedex getPokedex() {
        return pokedex;
    }

    public void setPokedex(Pokedex pokedex) {
        this.pokedex = pokedex;
    }

    public void paintPanels() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        int START_PANEL = getNavigatorInstance().getStartIndex();
        int SELECTED_PANEL_INDEX = getNavigatorInstance().getPanelIndex();
        PokemonPanel SELECTED_PANEL = getPokemonPanels().get(SELECTED_PANEL_INDEX);

        for (PokemonPanel panel : getPokemonPanels()) {
            panel.setPokemon(getPokedex().selectPokemon(START_PANEL++));
            boolean isSelected = panel.equals(SELECTED_PANEL);
            stylePanel(panel, isSelected);
        }

        updateEnlargedPanel();
    }

    public void stylePanel(PokemonPanel panel, boolean isSelected) {
        Color panelColor = isSelected ? PokemonPanels_COLOR_SELECTED() : PokemonPanels_COLOR_DEFAULT();
        Color textColor = isSelected ? PokemonPanelsText_COLOR_SELECTED() : PokemonPanelsText_COLOR_DEFAULT();

        panel.getPokemonPanel().setBackground(panelColor);
        panel.getComponents().setBackground(panelColor);
        panel.getPokemonName().setForeground(textColor);
        panel.getPokemonId().setForeground(textColor);
    }

    private void updateEnlargedPanel() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        int SOUND_LOOP_COUNT = 0;
        int SELECTED_POKEMON = getNavigatorInstance().getPokemonIndex();
        AudioHandler soundEffect = new AudioHandler("selectSoundEffect.wav");
        Pokemon selectedPokemon = getPokedex().selectPokemon(SELECTED_POKEMON);

        getEnlargedPokemonPanel().setPokemon(selectedPokemon);
        soundEffect.play(SOUND_LOOP_COUNT);
    }
}
