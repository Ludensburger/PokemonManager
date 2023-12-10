package pokemon.ui.PokedexGUI.components;

import pokemon.ui.PokedexGUI.actions.PanelPainter;
import pokemon.ui.PokedexGUI.actions.ResponsiveSearch;
import pokemon.ui.PokedexGUI.design.MenuGUIDesign;
import pokemon.util.AudioHandler;
import pokemon.util.FontHandler;
import pokemon.util.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SearchBar implements MenuGUIDesign {

    private JPanel searchPanel;
    private JTextField searchBar;
    private JLabel icon;
    private static final Dimension SEARCHBAR_SIZE_DEFAULT = (new Dimension(200, 100));
    private static final String FONT_NAME = "pokemonRedBlue.ttf";
    private static final float FONT_SIZE = 32f;
    private static final int ICON_WIDTH = 64;
    private static final int ICON_HEIGHT = 64;

    public SearchBar(PanelPainter panelPainter, JFrame mainMenuFrame, AudioHandler mainMenuMusic) throws IOException, FontFormatException {
        ImageIcon IMAGE_ICON = new ImageHandler().getScaledPokedexIcon(ICON_WIDTH, ICON_HEIGHT, "icon", "search.png");

        setSearchBar(new JTextField(), panelPainter, mainMenuFrame, mainMenuMusic);
        setIcon(new JLabel(IMAGE_ICON));
        setSearchPanel(new JPanel());
    }

    public JPanel getSearchPanel() {
        return searchPanel;
    }

    public JLabel getIcon() {
        return icon;
    }

    public void setSearchPanel(JPanel searchPanel) {
            searchPanel.setLayout(new BorderLayout());
            searchPanel.add(getIcon(), BorderLayout.WEST);
            searchPanel.add(getSearchBar(), BorderLayout.CENTER);

        this.searchPanel = searchPanel;
    }

    public JTextField getSearchBar() {
        return searchBar;
    }

    public void setSearchBar(JTextField searchBar, PanelPainter panelPainter, JFrame mainMenuFrame, AudioHandler mainMenuMusic) throws IOException, FontFormatException {
        Font TEXTFIELD_FONT = new FontHandler().getFont(FONT_NAME).deriveFont(FONT_SIZE);

            searchBar.addKeyListener(new ResponsiveSearch(panelPainter).addResponsiveSearch(searchBar, mainMenuFrame, mainMenuMusic));
            searchBar.setPreferredSize(SEARCHBAR_SIZE_DEFAULT);
            searchBar.setFont(TEXTFIELD_FONT);
            searchBar.setOpaque(false);
            searchBar.setBackground(SearchBar_COLOR_DEFAULT());
            searchBar.setForeground(SearchBarText_COLOR_DEFAULT());
            searchBar.setBorder(null);
            searchBar.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(0, 30, 0, 0),
                    searchBar.getBorder()));
        
        this.searchBar = searchBar;
    }

    public void setIcon(JLabel icon) throws IOException, FontFormatException {
        Font ICON_FONT = new FontHandler().getFont(FONT_NAME).deriveFont(FONT_SIZE);

            icon.setLayout(new BorderLayout());
            icon.setOpaque(true);
            icon.setBackground(Color.black);
            icon.setForeground(Color.white);
            icon.setFont(ICON_FONT);
            icon.setText(" FIND ");

        this.icon = icon;
    }

}

