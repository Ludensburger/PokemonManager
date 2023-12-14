package pokemon.ui.ViewPokemonGUI.design;

import java.awt.*;

public interface ViewPokemonGUIDesign {
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
