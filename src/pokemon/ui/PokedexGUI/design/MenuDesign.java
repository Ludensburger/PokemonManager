package pokemon.ui.PokedexGUI.design;

import java.awt.*;

/**
 *  Handles various components settings such as SIZE and COLOR
 */
public interface MenuDesign {
    default Color PokemonPanels_COLOR_DEFAULT() {
        return Color.white;
    }

    default Color PokemonPanels_COLOR_SELECTED() {
        return Color.red;
    }

    default Dimension PokemonPanels_SIZE_DEFAULT() {
        return new Dimension(560, 80);
    }

    default Dimension PokemonPanels_SIZE_ENLARGED() {
        return new Dimension(560, 560);
    }

    default Color PokemonPanelsText_COLOR_DEFAULT() {
        return Color.white;
    }

    default Color PokemonPanelsText_COLOR_SELECTED() {
        return Color.red;
    }

    default Dimension ComponentsContainer_SIZE_DEFAULT() {
        return new Dimension(400, 105);
    }

    default Dimension ComponentsContainer_SIZE_ENLARGED() {
        return new Dimension(560, 560);
    }

    default Color SearchBar_COLOR_DEFAULT() {
        return Color.gray;
    }

    default Dimension SearchBar_SIZE_DEFAULT() {
        return new Dimension(50, 100);
    }

    default Color SearchBarText_COLOR_DEFAULT() {
        return Color.black;
    }

    default Color AddPokemonButton_COLOR_DEFAULT() {
        return Color.black;
    }
    default Color AddPokemonButtonText_COLOR_DEFAULT() {
        return Color.white;
    }


    default Dimension AddPokemonButton_SIZE_DEFAULT() {
        return new Dimension(350, 100);
    }


}
