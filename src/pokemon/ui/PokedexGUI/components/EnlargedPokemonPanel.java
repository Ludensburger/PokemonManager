package pokemon.ui.PokedexGUI.components;

import pokemon.util.FontHandler;
import pokemon.util.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EnlargedPokemonPanel extends PokemonPanel {
    public EnlargedPokemonPanel() throws CloneNotSupportedException, IOException, FontFormatException {
        super.clone();
    }

    @Override
    public void setPokemonImage(Integer pokemonId) {
        int IMAGE_WIDTH = 512;
        int IMAGE_HEIGHT = 512;

        ImageIcon IMAGE = new ImageHandler().getPokemonImage(IMAGE_WIDTH, IMAGE_HEIGHT, pokemonId);

        this.getPokemonImage().setIcon(IMAGE);
    }

    @Override
    public void decoratePokemonPanel() throws IOException, FontFormatException {
        //  Setting fonts
        Font FONT = new FontHandler().getFont("pokemonRedBlue.ttf");
        getPokemonId().setFont(FONT);
        getPokemonName().setFont(FONT);

        //  Setting pokemon panel wrapper
        getPokemonPanel().setBackground(PokemonPanels_COLOR_DEFAULT());
        getPokemonPanel().setPreferredSize(PokemonPanels_SIZE_ENLARGED());
        getPokemonPanel().setOpaque(false);

        //  Setting components container
        getComponents().setPreferredSize(ComponentsContainer_SIZE_ENLARGED());
        getComponents().setLayout(new BorderLayout());
        getComponents().setOpaque(false);

        //  Adds background and components to Pokemon panel
        getPokemonPanel().add(getBackground());
        getPokemonPanel().add(getComponents());

        //  Fills components container with background (color), Pokemon image, Pokemon id, and Pokemon name
        getComponents().setBackground(PokemonPanels_COLOR_DEFAULT());
        getComponents().add(getPokemonImage(), BorderLayout.CENTER);
        getComponents().add(getPokemonId(), BorderLayout.NORTH);
        getComponents().add(getPokemonName(), BorderLayout.SOUTH);
        getPokemonName().setForeground(PokemonPanelsText_COLOR_DEFAULT());
        getPokemonId().setForeground(PokemonPanelsText_COLOR_DEFAULT());
    }
}
