package pokemon.ui.PokedexGUI.components;

import pokemon.ui.PokedexGUI.design.MenuDesign;
import pokemon.util.FontHandler;
import pokemon.util.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SearchBar implements MenuDesign {
    private final JTextField searchBar;

    private final JLabel icon;

    public SearchBar() throws IOException, FontFormatException {
        ImageIcon IMAGE_ICON = new ImageHandler().getPokedexImage("pikachu8bitres.gif");

        this.searchBar = new JTextField();
        this.icon = new JLabel();

        decorateSearchBar();
    }

    public JTextField getSearchField() {
        return searchBar;
    }

    public JLabel getIcon() {
        return icon;
    }

    public void decorateSearchBar() throws IOException, FontFormatException {
        // Getting font
        Font FONT = new FontHandler().getFont("pokemonRedBlue.ttf").deriveFont(64f);
        getIcon().setLayout(new BorderLayout());
        getIcon().add(getSearchField());
        // Setting search field
        getSearchField().setFont(FONT);
        getSearchField().setOpaque(false);
        getSearchField().setBackground(SearchBar_COLOR_DEFAULT());
        getSearchField().setForeground(SearchBarText_COLOR_DEFAULT());
        getSearchField().setBorder(null);
        getSearchField().setPreferredSize(SearchBar_SIZE_DEFAULT());
        getSearchField().setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 20, 0, 0),
                getSearchField().getBorder()));
    }
}

