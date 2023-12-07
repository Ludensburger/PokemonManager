package pokemon.ui.PokedexGUI;


import pokemon.Pokedex;
import pokemon.Pokemon;
import pokemon.ui.PokedexGUI.components.AddPokemonButton;
import pokemon.ui.PokedexGUI.components.EnlargedPokemonPanel;
import pokemon.ui.PokedexGUI.components.PokemonPanel;
import pokemon.ui.PokedexGUI.components.SearchBar;
import pokemon.ui.PokedexGUI.design.MenuDesign;
import pokemon.util.AudioHandler;
import pokemon.util.ImageHandler;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class PokedexGUI extends JFrame implements MenuDesign {
    private Pokedex pokedex;
    private IndexManager selectIndex;
    private JLabel labelBackground;
    private JPanel pokemonPanelsContainer;
    private JPanel headerContainer;
    private JButton addPokemonButton;
    private ArrayList<PokemonPanel> pokemonPanels;
    private SearchBar searchBar;
    private PokemonPanel enlargedPanel;

    public PokedexGUI() throws CloneNotSupportedException, IOException, FontFormatException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException {
        //  Handles JFrame settings
        //  Audio
        AudioInputStream BACKGROUND_MUSIC = new AudioHandler().getAudio("rustboro.wav");
        Clip clip = AudioSystem.getClip();
        clip.open(BACKGROUND_MUSIC);
        clip.start();
        
        //  Gets Icon
        ImageIcon ICON = new ImageIcon("src" + File.separator + "ico.png");  // retrieves icon
        ImageIcon BACKGROUND = new ImageHandler().getPokedexImage("test.gif");
        setLabelBackground(new JLabel(BACKGROUND));

        //  Starts Pokedex
        setPokedex(new Pokedex());
        getPokedex().startPokedex();

        this.setTitle("Pokedex");
        this.setIconImage(ICON.getImage());
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.add(getLabelBackground());


        //  Sets components
        setSelectIndex(IndexManager.getInstance());
        setPokemonPanelsContainer(new JPanel());
        setHeaderContainer(new JPanel());
        setAddPokemonButton(new AddPokemonButton().getAddPokemonButton());
        setPokemonPanels(getPanels());
        setSearchBar(new SearchBar());
        setEnlargedPanel(new EnlargedPokemonPanel());

        //  Loads components
        loadComponents();
    }

    public void setSelectIndex(IndexManager selectIndex) {
        this.selectIndex = selectIndex;
    }

    public void setPokemonPanelsContainer(JPanel pokemonPanelsContainer) {
        this.pokemonPanelsContainer = pokemonPanelsContainer;
    }

    public void setHeaderContainer(JPanel headerContainer) {
        this.headerContainer = headerContainer;
    }

    public void setAddPokemonButton(JButton addPokemonButton) {
        this.addPokemonButton = addPokemonButton;
    }

    public void setPokemonPanels(ArrayList<PokemonPanel> pokemonPanels) {
        this.pokemonPanels = pokemonPanels;
    }

    public void setSearchBar(SearchBar searchBar) {
        this.searchBar = searchBar;
    }

    public void setEnlargedPanel(PokemonPanel enlargedPanel) {
        this.enlargedPanel = enlargedPanel;
    }

    public void setPokedex(Pokedex pokedex) {
        this.pokedex = pokedex;
    }

    public JLabel getLabelBackground() {
        return labelBackground;
    }

    public void setLabelBackground(JLabel background) {
        this.labelBackground = background;
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

    public JPanel getHeaderContainer() { return headerContainer; }

    public JButton getAddPokemonButton() { return addPokemonButton;}
    public Pokedex getPokedex() {
        return pokedex;
    }


    private void loadComponents() throws CloneNotSupportedException {
        //  Gets JTextField from SearchBar
        JLabel searchFieldBackground = getSearchBar().getIcon();
        JTextField searchField = getSearchBar().getSearchField();
        getLabelBackground().setLayout(new BorderLayout());

        //  Sets container to Grid layout and instantiates individual panels
        getPokemonPanelsContainer().setLayout(new GridLayout(5,1));
        getPokemonPanelsContainer().setOpaque(false);
        for(PokemonPanel panel : getPokemonPanels()) {
            getPokemonPanelsContainer().add(panel.getPokemonPanel());
        }

        //  Sets "Add Pokemon" button
        getAddPokemonButton().setPreferredSize(AddPokemonButton_SIZE_DEFAULT());
        getAddPokemonButton().setText("Add Pokemon");

        // Sets header container which contains search field and "Add Pokemon" button
        getHeaderContainer().setLayout(new BorderLayout());
        getHeaderContainer().add(searchFieldBackground, BorderLayout.CENTER);
        getHeaderContainer().add(getAddPokemonButton(), BorderLayout.EAST);

        //  Adds all panels to our JFrame
        getLabelBackground().add(getHeaderContainer(), BorderLayout.NORTH);
        getLabelBackground().add(getEnlargedPanel().getPokemonPanel(), BorderLayout.CENTER);
        getLabelBackground().add(getPokemonPanelsContainer(), BorderLayout.EAST);

        //  When UI loads up, this indicates the selected panel to default to the first panel
        getPokemonPanels().get(0).getPokemonPanel().setBackground(PokemonPanels_COLOR_SELECTED());
        getPokemonPanels().get(0).getComponents().setBackground(PokemonPanels_COLOR_SELECTED());
        getPokemonPanels().get(0).getPokemonName().setForeground(PokemonPanelsText_COLOR_SELECTED());
        getPokemonPanels().get(0).getPokemonId().setForeground(PokemonPanelsText_COLOR_SELECTED());

        //  Defaults enlarged panel to first Pokemon
        getEnlargedPanel().setPokemon(getPokedex().selectPokemon(1));

        //  Iterates through all five (5) panels and initially sets them to the first five (5) Pokemon
        int count = 1;
        for(PokemonPanel panel : getPokemonPanels()) {
            panel.setPokemon(getPokedex().selectPokemon(count++));
        }

        //  Handles active searching
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                handleSearchKeyEvent();
            }
        });

        //  Handles mouse wheel searching
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                handleMouseWheelEvent(e);

            }
        });

        //  Handles KEY_UP and KEY_DOWN searching
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPressedEvent(e);
            }
        });
    }

    public void moveDown() {
        /*
         *  Logic for downward iteration of panels
         */

        //  Checks if the current Pokemon index is lesser than the number of Pokemon in our Pokedex
        //  to prevent going out of bounds
        if(getSelectIndex().getPokemonIndex() < getPokedex().getPokemonHashMap().size()) {
            getSelectIndex().incrementPokemon();

            //  Checks if the panel index is '4' which means we are at the last panel
            if(getSelectIndex().getPanelIndex() == 4) {
                // Increments our start index which handles the index where our first panel will
                // start printing our Pokemon from the hashmap
                getSelectIndex().incrementStart();
            }
        }

        //  Checks if our Panel index does not go out of bounds
        if(getSelectIndex().getPanelIndex() < getPokemonPanels().size() - 1) {
            getSelectIndex().incrementPanel();
        }
    }

    public void moveUp() {
        /*
         *  Logic for upward iteration of panels
         */

        //  Checks if current Pokemon index is greater than 1 to prevent going out of bounds
        if(getSelectIndex().getPokemonIndex() > 1) {
            getSelectIndex().decrementPokemon();
            
            //  Checks if the current panel is the first panel
            if(getSelectIndex().getPanelIndex() == 0) {
                //  Decrements start which handles the index where our Pokemon printing will begin
                //  from the hashmap
                getSelectIndex().decrementStart();
            }
        }
        
        //  Checks if our panel does not go out of bounds
        if(getSelectIndex().getPanelIndex() > 0) {
            getSelectIndex().decrementPanel();
        }
    }

    private void handleSearchKeyEvent() {
        //  Gets search field
        JTextField searchField = getSearchBar().getSearchField();
        
        //  Gets text from search field
        String SEARCH_FIELD_TEXT = searchField.getText().toUpperCase();
        
        //  Gets current Pokemon selected
        Integer CURRENT_PANEL = IndexManager.getInstance().getPokemonIndex();
        
        //  Initializes the integer that handles the location of where
        //  our Pokemon is found to one (1)
        Integer POKEMON_FOUND_PANEL = getFoundPokemonInteger(SEARCH_FIELD_TEXT);

        //  Checks if current panel is GREATER than the panel where
        //  our searched Pokemon is found
        if (CURRENT_PANEL > POKEMON_FOUND_PANEL) {
            while (CURRENT_PANEL > POKEMON_FOUND_PANEL) {
                //  Gradually moves UP till we reach the panel where
                //  our searched Pokemon is found
                CURRENT_PANEL--;
                moveUp();
            }
        } else {
            //  Checks if current panel is LESSER than the panel where
            //  our searched Pokemon is found
            while (CURRENT_PANEL < POKEMON_FOUND_PANEL) {
                //  Gradually moves DOWN till we reach the panel where
                //  our searched Pokemon is found
                CURRENT_PANEL++;
                moveDown();
            }
        }

        try {
            updatePanels();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Integer getFoundPokemonInteger(String SEARCH_FIELD_TEXT) {
        int POKEMON_FOUND_PANEL = 1;

        //  Checks if search field has value
        if (!SEARCH_FIELD_TEXT.isEmpty()) {
            //  Iterates through all Pokemon objects and finds matching names
            for (Pokemon pokemon : getPokedex().getPokemonHashMap().values()) {
                if (pokemon.getName().contains(SEARCH_FIELD_TEXT)) {
                    //  If Pokemon is found, returns their ID
                    POKEMON_FOUND_PANEL = pokemon.getId();
                    return POKEMON_FOUND_PANEL;
                }
            }
        }

        return POKEMON_FOUND_PANEL;
    }


    private void handleMouseWheelEvent(MouseWheelEvent e) {
        int WHEEL_ROTATION = e.getWheelRotation();

        //  Downward motion of scroll wheel returns 1
        if (WHEEL_ROTATION > 0) {
            moveDown();

        } else {
            //  Upward motion of scroll wheel returns -1
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
        int FIRST_PANEL = selectIndex.getStartIndex();

        //  Iterates through all panels to set their color
        for(PokemonPanel panel : getPokemonPanels()) {
            //  Sets each panel to corresponding Pokemon by index
            panel.setPokemon(getPokedex().selectPokemon(FIRST_PANEL++));

            //  Highlights our current panel indicator
            if(panel.getPokemonPanel().equals(getPokemonPanels().get(selectIndex.getPanelIndex()).getPokemonPanel())) {
                panel.getPokemonPanel().setBackground(PokemonPanels_COLOR_SELECTED());
                panel.getComponents().setBackground(PokemonPanels_COLOR_SELECTED());

                panel.getPokemonName().setForeground(PokemonPanelsText_COLOR_SELECTED());
                panel.getPokemonId().setForeground(PokemonPanelsText_COLOR_SELECTED());
            } else {
                //  Ensures other panels that are not the current panel have their
                //  colors updated
                panel.getPokemonPanel().setBackground(PokemonPanels_COLOR_DEFAULT());
                panel.getComponents().setBackground(PokemonPanels_COLOR_DEFAULT());

                panel.getPokemonName().setForeground(PokemonPanelsText_COLOR_DEFAULT());
                panel.getPokemonId().setForeground(PokemonPanelsText_COLOR_DEFAULT());
            }
        }

        //  Updates our enlarged panel
        getEnlargedPanel().setPokemon(getPokedex().selectPokemon(selectIndex.getPokemonIndex()));

        AudioInputStream SELECT_SOUND = null;
        try {
            SELECT_SOUND = new AudioHandler().getAudio("selectSoundEffect.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            throw new RuntimeException(ex);
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException ex) {
            throw new RuntimeException(ex);
        }
        try {
            clip.open(SELECT_SOUND);
        } catch (LineUnavailableException | IOException ex) {
            throw new RuntimeException(ex);
        }
        clip.start();
    }

    private ArrayList<PokemonPanel> getPanels() throws CloneNotSupportedException, IOException, FontFormatException {
        /*
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
