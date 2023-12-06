package pokemon.ui.PokedexGUI;


import pokemon.Pokedex;
import pokemon.Pokemon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class PokedexGUI extends JFrame {
    private final Pokedex pokedex = new Pokedex();
    private final IndexManager selectIndex = IndexManager.getInstance();
    private final ArrayList<PokemonPanel> pokemonPanels = getPanels();
    private final SearchBar searchBar = new SearchBar();
    private final PokemonPanel enlargedPanel = new EnlargedPokemonPanel();
    private final JPanel pokemonPanelsContainer = new JPanel();

    public PokedexGUI() throws CloneNotSupportedException, IOException, FontFormatException, ClassNotFoundException {
        /**
         *  Runs readPokeFile function and retrieves data from "pokedex_data.txt"
         *  Does not read instantly in case user wants empty Pokedex
         */
        getPokedex().startPokedex();

        ImageIcon icon = new ImageIcon("src" + File.separator + "ico.png");  // retrieves icon


        this.setTitle("Pokedex");
        this.setIconImage(icon.getImage());
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


        load();
    }

    public Pokedex getPokedex() {
        return pokedex;
    }

    public IndexManager getSelectIndex() {
        return selectIndex;
    }

    public ArrayList<PokemonPanel> getPokemonPanels() {
        return pokemonPanels;
    }

    public SearchBar getSearchBar() {
        return searchBar;
    }

    public PokemonPanel getEnlargedPanel() {
        return enlargedPanel;
    }

    public JPanel getPokemonPanelsContainer() {
        return pokemonPanelsContainer;
    }



    private void load() throws CloneNotSupportedException {
        JTextField searchField = getSearchBar().getSearchField();
        getPokemonPanelsContainer().setLayout(new GridLayout(5,1,1,1));

        super.add(searchField, BorderLayout.NORTH);

        super.add(getEnlargedPanel().getPokemonPanel(), BorderLayout.CENTER);

        for(PokemonPanel panel : getPokemonPanels()) {
            getPokemonPanelsContainer().add(panel.getPokemonPanel());
        }

        super.add(getPokemonPanelsContainer(), BorderLayout.EAST);


        /*
            When menu loads up, selected Pokemon indicator starts at the very first panel
         */
        getPokemonPanels().get(0).getPokemonPanel().setBackground(Color.red);
        getEnlargedPanel().setPokemon(getPokedex().selectPokemon(1));

        int count = 1;
        for(PokemonPanel panel : getPokemonPanels()) {
            panel.setPokemon(getPokedex().selectPokemon(count++));
        }
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                handleSearchKeyEvent();
            }
        });

        addMouseWheelListener(this::handleMouseWheelEvent);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPressedEvent(e);
            }
        });
    }

    public void moveDown() {
        if(getSelectIndex().getPokemonIndex() < getPokedex().getPokemonHashMap().size()) {
            getSelectIndex().incrementPokemon();

            if(getSelectIndex().getPanelIndex() == 4) {
                getSelectIndex().incrementStart();
            }
        }

        if(getSelectIndex().getPanelIndex() < getPokemonPanels().size() - 1) {
            getSelectIndex().incrementPanel();
        }
    }

    public void moveUp() {
        if(getSelectIndex().getPokemonIndex() > 1) {
            getSelectIndex().decrementPokemon();

            if(getSelectIndex().getPanelIndex() == 0) {
                getSelectIndex().decrementStart();
            }
        }

        if(getSelectIndex().getPanelIndex() > 0) {
            getSelectIndex().decrementPanel();
        }
    }

    private void handleSearchKeyEvent() {
        JTextField searchField = getSearchBar().getSearchField();
        String find = searchField.getText().toUpperCase();
        Integer currentIndex = IndexManager.getInstance().getPokemonIndex();
        Integer nextIndex = 1;

        if (find.isEmpty()) {
            nextIndex = 1;
        } else {
            for (Pokemon pokemon : getPokedex().getPokemonHashMap().values()) {
                if (pokemon.getName().contains(find)) {
                    nextIndex = pokemon.getId();
                }
            }
        }

        if (currentIndex > nextIndex) {
            while (currentIndex > nextIndex) {
                currentIndex--;
                moveUp();
            }
        } else {
            while (currentIndex < nextIndex) {
                currentIndex++;
                moveDown();
            }
        }

        try {
            updatePanels();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }


    private void handleMouseWheelEvent(MouseWheelEvent e) {
        int amount = e.getWheelRotation();

        if (amount > 0) {
            moveDown();

        } else {
            moveUp();
        }

        try {
            updatePanels();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void handleKeyPressedEvent(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            moveUp();
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            moveDown();
        }

        try {
            updatePanels();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void updatePanels() throws CloneNotSupportedException {
        IndexManager selectIndex = IndexManager.getInstance();

        for(PokemonPanel panel : getPokemonPanels()) {
            if(panel.getPokemonPanel().equals(getPokemonPanels().get(selectIndex.getPanelIndex()).getPokemonPanel())) {
                panel.getPokemonPanel().setBackground(Color.red);
            } else {
                panel.getPokemonPanel().setBackground(Color.black);
            }

            getEnlargedPanel().setPokemon(getPokedex().selectPokemon(selectIndex.getPokemonIndex()));
        }


        int startIdx = selectIndex.getStartIndex();

        for(PokemonPanel panel : getPokemonPanels()) {
            panel.setPokemon(getPokedex().selectPokemon(startIdx++));
        }
    }

    private ArrayList<PokemonPanel> getPanels() throws CloneNotSupportedException, IOException, FontFormatException {
        /**
         *  Prototype design pattern to clone existing pokemonPanel into five (5)
         *  more to be used in visually aiding navigation through Pokemon hashmap
         */
        ArrayList<PokemonPanel> pokemonPanels = new ArrayList<>();

        PokemonPanel pokemonPanel = new PokemonPanel();

        PokemonPanel pokemonPanel1 = pokemonPanel.clone();
        PokemonPanel pokemonPanel2 = pokemonPanel.clone();
        PokemonPanel pokemonPanel3 = pokemonPanel.clone();
        PokemonPanel pokemonPanel4 = pokemonPanel.clone();
        PokemonPanel pokemonPanel5 = pokemonPanel.clone();

        pokemonPanels.add(pokemonPanel1);
        pokemonPanels.add(pokemonPanel2);
        pokemonPanels.add(pokemonPanel3);
        pokemonPanels.add(pokemonPanel4);
        pokemonPanels.add(pokemonPanel5);
        return pokemonPanels;
    }
}
