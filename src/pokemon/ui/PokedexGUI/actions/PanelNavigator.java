package pokemon.ui.PokedexGUI.actions;

import pokemon.Pokemon;
import pokemon.ui.PokedexGUI.components.PokemonPanel;

import java.util.ArrayList;
import java.util.HashMap;

public class PanelNavigator {
    private static PanelNavigator instance;
    private Integer panelIndex = 0;
    private Integer pokemonIndex = 1;
    private Integer startIndex = 1;

    private PanelNavigator() {}

    public static PanelNavigator getInstance() {
        if (instance == null) {
            instance = new PanelNavigator();
        }
        return instance;
    }

    public Integer getPanelIndex() {
        return panelIndex;
    }

    public Integer getPokemonIndex() {
        return pokemonIndex;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void incrementPanel() {
        panelIndex++;
    }

    public void decrementPanel() {
        panelIndex--;
    }

    public void incrementPokemon() {
        pokemonIndex++;
    }

    public void decrementPokemon(){
        pokemonIndex--;
    }

    public void incrementStart() {
        startIndex++;
    }

    public void decrementStart() {
        startIndex--;
    }

    public void moveDown(int MAX_POKEMON, int MAX_PANELS) {
        /*
         *  Logic for downward iteration of panels
         */

        //  Checks if the current Pokemon index is lesser than the number of Pokemon in our Pokedex
        //  to prevent going out of bounds
        if(getPokemonIndex() < MAX_POKEMON) {
            incrementPokemon();

            //  Checks if the panel index is '4' which means we are at the last panel
            if(getPanelIndex() == 4) {
                // Increments our start index which handles the index where our first panel will
                // start printing our Pokemon from the hashmap
                incrementStart();
            }
        }

        //  Checks if our Panel index does not go out of bounds
        if(getPanelIndex() < MAX_PANELS - 1) {
            incrementPanel();
        }

    }

    public void moveUp() {
        /*
         *  Logic for upward iteration of panels
         */

        //  Checks if current Pokemon index is greater than 1 to prevent going out of bounds
        if(getPokemonIndex() > 1) {
            decrementPokemon();

            //  Checks if the current panel is the first panel
            if(getPanelIndex() == 0) {
                //  Decrements start which handles the index where our Pokemon printing will begin
                //  from the hashmap
                decrementStart();
            }
        }

        //  Checks if our panel does not go out of bounds
        if(getPanelIndex() > 0) {
            decrementPanel();
        }
    }
}
