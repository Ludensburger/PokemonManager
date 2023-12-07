package pokemon.ui.PokedexGUI.components;

import pokemon.ui.PokedexGUI.design.MenuDesign;
import pokemon.util.FontHandler;
import pokemon.util.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AddPokemonButton implements MenuDesign {
    private final JButton addPokemonButton;
    private final JLabel icon;

    public AddPokemonButton() throws IOException, FontFormatException {
        ImageIcon IMAGE_ICON = new ImageHandler().getPokedexImage("pikachu8bitres.gif");

        this.addPokemonButton = new JButton();
        this.icon = new JLabel(IMAGE_ICON);

        decorateAddButton();
    }

    public JButton getAddPokemonButton() {
        return addPokemonButton;
    }

    public JLabel getIcon() {
        return icon;
    }

    public void decorateAddButton() throws IOException, FontFormatException {
        //  Getting font
        Font FONT = new FontHandler().getFont("pokemonRedBlue.ttf");
        getAddPokemonButton().add(getIcon());
        getIcon().setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 10, 0, 0),
                getAddPokemonButton().getBorder()));
        getAddPokemonButton().setBackground(AddPokemonButton_COLOR_DEFAULT());
        getAddPokemonButton().setPreferredSize(AddPokemonButton_SIZE_DEFAULT());
        getAddPokemonButton().setFont(FONT);
        getAddPokemonButton().setBorder(null);
        getAddPokemonButton().setBorderPainted(false);
        getAddPokemonButton().setFocusPainted(false);
        getAddPokemonButton().setForeground(AddPokemonButtonText_COLOR_DEFAULT());
        getAddPokemonButton().setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 0, 0, 20),
                getAddPokemonButton().getBorder()));
    }
}
