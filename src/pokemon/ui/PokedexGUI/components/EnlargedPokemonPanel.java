package pokemon.ui.PokedexGUI.components;

import pokemon.util.FontHandler;
import pokemon.util.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EnlargedPokemonPanel extends PokemonPanel {
    private JPanel components;

    private JPanel pokemonPanel;

    private static final Dimension PANEL_SIZE_ENLARGED = (new Dimension(560, 560));
    private static final Dimension COMPONENT_SIZE_ENLARGED = (new Dimension(560, 560));
    private static final int IMAGE_WIDTH_ENLARGED = 512;
    private static final int IMAGE_HEIGHT_ENLARGED = 512;


    public EnlargedPokemonPanel() throws IOException, FontFormatException {
        super();
    }

    @Override
    public void setPokemonImageIcon(Integer pokemonId) {
        ImageIcon IMAGE = new ImageHandler().getPokemonImage(IMAGE_WIDTH_ENLARGED, IMAGE_HEIGHT_ENLARGED, pokemonId);
        this.getPokemonImage().setIcon(IMAGE);
    }

    @Override
    public void setPokemonPanel(JPanel pokemonPanel) {
        pokemonPanel.setPreferredSize(PANEL_SIZE_ENLARGED);
        pokemonPanel.setOpaque(false);

        pokemonPanel.add(getBackground());
        pokemonPanel.add(getComponents());

        this.pokemonPanel = pokemonPanel;
    }

    @Override
    public void setComponents(JPanel componentsPanel) {
        componentsPanel.setPreferredSize(COMPONENT_SIZE_ENLARGED);
        componentsPanel.setBackground(PokemonPanels_COLOR_DEFAULT());
        componentsPanel.setOpaque(false);
        componentsPanel.setLayout(new BorderLayout());

        componentsPanel.add(getPokemonImage(), BorderLayout.CENTER);
        componentsPanel.add(getPokemonId(), BorderLayout.NORTH);
        componentsPanel.add(getPokemonName(), BorderLayout.SOUTH);

        this.components = componentsPanel;
    }

    @Override
    public JPanel getComponents() { return components; }

    @Override
    public JPanel getPokemonPanel() {
        return pokemonPanel;
    }
}
