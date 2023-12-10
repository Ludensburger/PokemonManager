package pokemon.ui.PokedexGUI.design;

import java.awt.*;

/**
 *  Handles various components settings such as SIZE and COLOR
 */
public interface MenuGUIDesign {
    default Color PokemonPanels_COLOR_DEFAULT() {
        return Color.black;
    }

    default Color PokemonPanels_COLOR_SELECTED() {
        return Color.red;
    }

    default Color PokemonPanelsText_COLOR_DEFAULT() {
        return Color.white;
    }

    default Color PokemonPanelsText_COLOR_SELECTED() {
        return Color.white;
    }

    default Color SearchBar_COLOR_DEFAULT() {
        return Color.white;
    }

    default Color SearchBarText_COLOR_DEFAULT() {
        return Color.black;
    }

    default Color AddPokemonButton_COLOR_DEFAULT() {
        return Color.black;
    }

    default Color AddPokemonButton_COLOR_SELECTED() {
        return Color.red;
    }

    default Color AddPokemonButtonText_COLOR_DEFAULT() {
        return Color.white;
    }

    default Color TitleBar_COLOR_DEFAULT() {
        return Color.black;
    }

    default Color TitleBarText_COLOR_DEFAULT() {
        return Color.white;
    }

    default Color TitleBarButton_COLOR_SELECTED() {
        return Color.red;
    }
}
