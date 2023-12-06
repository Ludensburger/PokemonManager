package pokemon.ui.PokedexGUI;

import pokemon.util.FontHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SearchBar {
    private final JTextField searchBar;

    public SearchBar() throws IOException, FontFormatException {
        this.searchBar = new JTextField();
        initializeSearchBar();
    }

    public JTextField getSearchField() {
        return searchBar;
    }

    public void initializeSearchBar() throws IOException, FontFormatException {
        Font PokemonRB = new FontHandler().getFont("PokemonRB.ttf");
        getSearchField().setBackground(Color.gray);
        getSearchField().setPreferredSize(new Dimension(50,100));
        getSearchField().setText("");
        getSearchField().setFont(PokemonRB);
    }
}

