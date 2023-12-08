package pokemon.ui.PokedexGUI.components;

import pokemon.ui.PokedexGUI.design.MenuDesign;
import pokemon.util.FontHandler;
import pokemon.util.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SearchBar implements MenuDesign {

    private final JPanel searchPanel;
    private final JTextField searchBar;
    private final JLabel icon;

    public SearchBar() throws IOException, FontFormatException {
        ImageIcon IMAGE_ICON = new ImageHandler().getScaledPokedexIcon(100, 100, "icon", "search.png");

        this.searchPanel = new JPanel();
        this.searchBar = new JTextField();
        this.icon = new JLabel(IMAGE_ICON);

        decorateSearchBar();
    }

    public JTextField getSearchField() {
        return searchBar;
    }

    public JPanel getSearchPanel() {
        return searchPanel;
    }

    public JLabel getIcon() {
        return icon;
    }

    public void decorateSearchBar() throws IOException, FontFormatException {
        // Getting font
        Font FONT_LARGE = new FontHandler().getFont("pokemonRedBlue.ttf").deriveFont(64f);
        Font FONT_SMALL = new FontHandler().getFont("pokemonRedBlue.ttf").deriveFont(32f);
        //  Panel
        getSearchPanel().setLayout(new BorderLayout());
        getSearchPanel().add(getIcon(), BorderLayout.WEST);

        //  Icon
        getIcon().setLayout(new BorderLayout());
        getIcon().setOpaque(true);
        getIcon().setBackground(Color.black);
        getIcon().setText(" FIND ");
        getIcon().setForeground(Color.white);
        getIcon().setFont(FONT_SMALL);
        getSearchPanel().add(getSearchField(), BorderLayout.CENTER);

        // Setting search field
        getSearchField().setPreferredSize(new Dimension(1000, 100));
        getSearchField().setFont(FONT_LARGE);
        getSearchField().setOpaque(false);
        getSearchField().setBackground(SearchBar_COLOR_DEFAULT());
        getSearchField().setForeground(SearchBarText_COLOR_DEFAULT());
        getSearchField().setBorder(null);
        getSearchField().setPreferredSize(SearchBar_SIZE_DEFAULT());
        getSearchField().setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 30, 0, 0),
                getSearchField().getBorder()));
    }
}

