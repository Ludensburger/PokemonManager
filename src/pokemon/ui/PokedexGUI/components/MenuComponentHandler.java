package pokemon.ui.PokedexGUI.components;

import pokemon.Pokedex;
import pokemon.ui.PokedexGUI.actions.PanelNavigator;
import pokemon.ui.PokedexGUI.actions.PanelPainter;
import pokemon.util.AudioHandler;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MenuComponentHandler {
    private JLabel labelBackground;
    private JPanel pokemonPanelsContainer;
    private JPanel headerContainer;
    private JPanel titleBarContainer;
    private JPanel enlargedPanelContainer;
    private ArrayList<PokemonPanel> pokemonPanels;
    private EnlargedPokemonPanel enlargedPokemonPanel;
    private PanelPainter panelPainter;
    private final PanelNavigator panelNavigator = PanelNavigator.getInstance();

    public MenuComponentHandler(JFrame frame, Pokedex pokedex, ImageIcon backgroundImage, AudioHandler mainMenuMusic) throws IOException, FontFormatException, CloneNotSupportedException, UnsupportedAudioFileException, LineUnavailableException {

        setEnlargedPanelContainer(new JPanel(), pokedex);
        setPokemonPanels(pokedex, 5);

        setPanelPainter(PanelPainter.getInstance());
        getPanelPainter().setEnlargedPokemonPanel(getEnlargedPokemonPanel());
        getPanelPainter().setNavigatorInstance(getPanelNavigator());
        getPanelPainter().setPokedex(pokedex);

        setPokemonPanelsContainer(new JPanel(), pokedex);
        setTitleBarContainer(new MenuTitleBar(frame).getTitlePanel());
        setHeaderContainer(new JPanel(), frame, mainMenuMusic);
        setLabelBackground(new JLabel(backgroundImage));

        getPanelPainter().setPokemonPanels(getPokemonPanels());
    }

    public JLabel getLabelBackground() {
        return labelBackground;
    }

    public void setLabelBackground(JLabel labelBackground) {
        labelBackground.setLayout(new BorderLayout());
        labelBackground.add(getHeaderContainer(), BorderLayout.NORTH);
        labelBackground.add(getEnlargedPanelContainer(), BorderLayout.CENTER);
        labelBackground.add(getPokemonPanelsContainer(), BorderLayout.EAST);


        this.labelBackground = labelBackground;
    }

    public JPanel getPokemonPanelsContainer() {
        return pokemonPanelsContainer;
    }

    public void setPokemonPanelsContainer(JPanel pokemonPanelsContainer, Pokedex pokedex) throws IOException, FontFormatException, CloneNotSupportedException {
        int AMOUNT_OF_PANELS = 5;
        setPokemonPanels(pokedex, AMOUNT_OF_PANELS);

        configurePokemonPanelsContainer(pokemonPanelsContainer);

        this.pokemonPanelsContainer = pokemonPanelsContainer;
    }

    private void configurePokemonPanelsContainer(JPanel pokemonPanelsContainer) {
        int GRID_ROWS = 5;
        int GRID_COLS = 1;

        pokemonPanelsContainer.setLayout(new GridLayout(GRID_ROWS, GRID_COLS));
        pokemonPanelsContainer.setOpaque(false);
        pokemonPanelsContainer.setVisible(true);

        for (PokemonPanel panel : getPokemonPanels()) {
            pokemonPanelsContainer.add(panel.getPokemonPanel());
        }

        getPanelPainter().stylePanel(getPokemonPanels().get(0), true);
    }

    public JPanel getHeaderContainer() {
        return headerContainer;
    }

    public void setHeaderContainer(JPanel headerContainer, JFrame frame, AudioHandler mainMenuMusic) throws IOException, FontFormatException, LineUnavailableException {
        SearchBar searchBar = new SearchBar(getPanelPainter(), frame, mainMenuMusic);
        AddPokemonButton pokemonButton = new AddPokemonButton(frame, mainMenuMusic);

        headerContainer.setLayout(new BorderLayout());
        headerContainer.add(getTitleBarContainer(),  BorderLayout.NORTH);
        headerContainer.add(searchBar.getSearchPanel(), BorderLayout.CENTER);
        headerContainer.add(pokemonButton.getAddPokemonButton(), BorderLayout.EAST);

        this.headerContainer = headerContainer;
    }

    public JPanel getTitleBarContainer() {
        return titleBarContainer;
    }

    public void setTitleBarContainer(JPanel titleBarContainer) {
        this.titleBarContainer = titleBarContainer;
    }

    public JPanel getEnlargedPanelContainer() {
        return enlargedPanelContainer;
    }

    public void setEnlargedPanelContainer(JPanel enlargedPanelContainer, Pokedex pokedex) throws IOException, FontFormatException {
        setEnlargedPokemonPanel(new EnlargedPokemonPanel());
        getEnlargedPokemonPanel().setPokemon(pokedex.selectPokemon(1));

        enlargedPanelContainer.setLayout(new BorderLayout());
        enlargedPanelContainer.setOpaque(false);
        enlargedPanelContainer.add(getEnlargedPokemonPanel().getPokemonPanel());

        this.enlargedPanelContainer = enlargedPanelContainer;
    }

    public ArrayList<PokemonPanel> getPokemonPanels() {
        return pokemonPanels;
    }

    public void setPokemonPanels(Pokedex pokedex, int amountOfPanels) throws IOException, FontFormatException, CloneNotSupportedException {
        ArrayList<PokemonPanel> pokemonPanels = new ArrayList<>();

        for (int i = 0; i < amountOfPanels; i++) {
            PokemonPanel panel = new PokemonPanel();
            panel.setPokemon(pokedex.selectPokemon(i + 1));
            pokemonPanels.add(panel);
        }

        this.pokemonPanels = pokemonPanels;
    }

    public EnlargedPokemonPanel getEnlargedPokemonPanel() {
        return enlargedPokemonPanel;
    }

    public void setEnlargedPokemonPanel(EnlargedPokemonPanel enlargedPokemonPanel) {
        this.enlargedPokemonPanel = enlargedPokemonPanel;
    }

    public PanelPainter getPanelPainter() {
        return panelPainter;
    }

    public void setPanelPainter(PanelPainter panelPainter) {
        this.panelPainter = panelPainter;
    }

    public PanelNavigator getPanelNavigator() {
        return panelNavigator;
    }

}
