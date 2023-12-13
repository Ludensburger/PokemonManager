package pokemon.ui.PokedexGUI.components;

import pokemon.Pokemon;
import pokemon.ui.PokedexGUI.design.MenuGUIDesign;
import pokemon.ui.PokedexGUI.design.PanelColors;
import pokemon.util.FontHandler;
import pokemon.util.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PokemonPanel implements MenuGUIDesign, PanelColors {
    private JPanel pokemonPanel;
    private JPanel components;
    private JLabel background;
    private JLabel pokemonId;
    private JLabel pokemonName;
    private JLabel pokemonImage;
    private Pokemon pokemon;
    private Font font;

    private static final Dimension PANEL_SIZE_DEFAULT = (new Dimension(560, 80));
    private static final Dimension COMPONENT_SIZE_DEFAULT = (new Dimension(400, 105));
    private static final int IMAGE_WIDTH_DEFAULT = 86;
    private static final int IMAGE_HEIGHT_DEFAULT = 86;
    private static final String FONT_NAME = "pokemonRedBlue.ttf";
    private static final float FONT_SIZE = 16f;


    public PokemonPanel() throws IOException, FontFormatException {
        setFont(new FontHandler().getFont(FONT_NAME, FONT_SIZE));
        setPokemonId(new JLabel());
        setPokemonName(new JLabel());
        setPokemonImage(new JLabel());
        setComponents(new JPanel());
        setBackground(new JLabel());
        setPokemonPanel(new JPanel());
    }
    public JPanel getPokemonPanel() {
        return pokemonPanel;
    }

    public JLabel getPokemonId() {
        return pokemonId;
    }
    public JLabel getPokemonName() {
        return pokemonName;
    }

    public JLabel getPokemonImage() {
        return pokemonImage;
    }

    public JPanel getComponents() { return components; }

    public JLabel getBackground() { return background; }


    public void setPokemonPanel(JPanel pokemonPanel) {

            pokemonPanel.setPreferredSize(PANEL_SIZE_DEFAULT);
            pokemonPanel.setBackground(PANEL_BACKGROUND_COLOR_DEFAULT);
            pokemonPanel.setOpaque(false);

            pokemonPanel.add(getBackground());
            pokemonPanel.add(getComponents());

        this.pokemonPanel = pokemonPanel;
    }

    public void setComponents(JPanel componentsPanel) {

            componentsPanel.setPreferredSize(COMPONENT_SIZE_DEFAULT);
            componentsPanel.setBackground(PANEL_BACKGROUND_COLOR_DEFAULT);
            componentsPanel.setOpaque(true);
            componentsPanel.setLayout(new BorderLayout());

            componentsPanel.add(getPokemonImage(), BorderLayout.WEST);
            componentsPanel.add(getPokemonId(), BorderLayout.CENTER);
            componentsPanel.add(getPokemonName(), BorderLayout.EAST);

        this.components = componentsPanel;
    }

    public void setBackground(JLabel backgroundLabel) {
        this.background = backgroundLabel;
    }

    public void setPokemonId(JLabel pokemonId) {

            pokemonId.setForeground(PANEL_FOREGROUND_COLOR_DEFAULT);
            pokemonId.setFont(getFont());
            pokemonId.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(0, 20, 0, 0),
                    pokemonId.getBorder()));

        this.pokemonId = pokemonId;
    }

    public void setPokemonName(JLabel pokemonName) {

            pokemonName.setForeground(PANEL_FOREGROUND_COLOR_DEFAULT);
            pokemonName.setFont(getFont());
            pokemonName.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(0, 0, 0, 30),
                    pokemonName.getBorder()));

        this.pokemonName = pokemonName;
    }

    public void setPokemonImage(JLabel pokemonImage) {
            pokemonImage.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(0, 20, 0, 0),
                    pokemonImage.getBorder()));

        this.pokemonImage = pokemonImage;
    }

    public void setPokemonIdText(Integer pokemonId) {
        //  Format changes from '1' to '001'
        this.getPokemonId().setText(String.format("%03d", pokemonId));
    }

    public void setPokemonNameText(String pokemonName) {
        this.getPokemonName().setText(pokemonName);
    }

    public void setPokemonImageIcon(Integer pokemonId) {
        //  Set image resolution
        ImageIcon IMAGE = new ImageHandler().getPokemonImage(IMAGE_WIDTH_DEFAULT, IMAGE_HEIGHT_DEFAULT, pokemonId, false);
        this.getPokemonImage().setIcon(IMAGE);
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
        setPokemonIdText(pokemon.getId());
        setPokemonNameText(pokemon.getName());
        setPokemonImageIcon(pokemon.getId());
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

}

