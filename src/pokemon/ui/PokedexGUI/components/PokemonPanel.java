package pokemon.ui.PokedexGUI.components;

import pokemon.Pokemon;
import pokemon.ui.PokedexGUI.design.MenuDesign;
import pokemon.util.FontHandler;
import pokemon.util.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PokemonPanel implements Cloneable, MenuDesign {
    private final JPanel pokemonPanel;
    private final JPanel components;
    private final JLabel background;
    private final JLabel pokemonId;
    private final JLabel pokemonName;
    private final JLabel pokemonImage;

    private Pokemon pokemon;


    public PokemonPanel() throws IOException, FontFormatException {
        this.pokemonPanel = new JPanel();
        this.pokemonId = new JLabel();
        this.pokemonName = new JLabel();
        this.pokemonImage = new JLabel();
        this.components = new JPanel();
        this.background = new JLabel();

        decoratePokemonPanel();
    }

    public void decoratePokemonPanel() throws FontFormatException, IOException {
        //  Setting fonts
        Font FONT = new FontHandler().getFont("pokemonRedBlue.ttf");
        getPokemonId().setFont(FONT);
        getPokemonName().setFont(FONT);

        //  Setting pokemon panel wrapper
        getPokemonPanel().setBackground(PokemonPanels_COLOR_DEFAULT());
        getPokemonPanel().setPreferredSize(PokemonPanels_SIZE_DEFAULT());
        getPokemonPanel().setOpaque(false);

        //  Setting components container
        getComponents().setPreferredSize(ComponentsContainer_SIZE_DEFAULT());
        getComponents().setLayout(new BorderLayout());
        getComponents().setOpaque(true);

        //  Adds background and components to Pokemon panel
        getPokemonPanel().add(getBackground());
        getPokemonPanel().add(getComponents());

        //  Fills components container with background (color), Pokemon image, Pokemon id, and Pokemon name
        getComponents().setBackground(PokemonPanels_COLOR_DEFAULT());
        getComponents().add(getPokemonImage(), BorderLayout.WEST);
        getComponents().add(getPokemonId(), BorderLayout.CENTER);
        getComponents().add(getPokemonName(), BorderLayout.EAST);
        getPokemonName().setForeground(PokemonPanelsText_COLOR_DEFAULT());
        getPokemonId().setForeground(PokemonPanelsText_COLOR_DEFAULT());

        //  images border
        getPokemonImage().setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 20, 0, 0),
                getPokemonImage().getBorder()));

        //  id border
        getPokemonId().setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 20, 0, 0),
                getPokemonId().getBorder()));

        //  name border
        getPokemonName().setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 0, 0, 30),
                getPokemonName().getBorder()));
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

    public void setPokemonId(Integer pokemonId) {
        //  Format changes from '1' to '001'
        this.getPokemonId().setText(String.format("%03d", pokemonId));
    }

    public void setPokemonName(String pokemonName) {
        this.getPokemonName().setText(pokemonName);
    }

    public void setPokemonImage(Integer pokemonId) {
        //  Set image resolution
        int IMAGE_WIDTH = 86;
        int IMAGE_HEIGHT = 86;

        ImageIcon IMAGE = new ImageHandler().getPokemonImage(IMAGE_WIDTH, IMAGE_HEIGHT, pokemonId);
        this.getPokemonImage().setIcon(IMAGE);
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
        setPokemonId(pokemon.getId());
        setPokemonName(pokemon.getName());
        setPokemonImage(pokemon.getId());
    }

    public Pokemon getPokemon() {
        return pokemon;
    }
    @Override
    public PokemonPanel clone() throws CloneNotSupportedException {
        PokemonPanel clone = (PokemonPanel) super.clone();
        try {
            return new PokemonPanel();
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }
}

