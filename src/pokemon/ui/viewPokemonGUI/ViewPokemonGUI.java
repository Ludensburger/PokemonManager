package pokemon.ui.viewPokemonGUI;


import pokemon.Pokedex;
import pokemon.Pokemon;
import pokemon.ui.addPokemonGUI.design.AddPokemonDesign;
import pokemon.ui.viewPokemonGUI.components.ViewPokemonComponentHandler;
import pokemon.util.ImageHandler;


import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;


public class ViewPokemonGUI extends JFrame implements AddPokemonDesign {
    private JFrame viewPokemonMenu;


    public ViewPokemonGUI(Pokemon pokemon) throws IOException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        //  Set mainMenu to this JFrame
        setViewPokemonMenu(this);

        int ICON_WIDTH = 64;
        int ICON_HEIGHT = 64;

        int MENU_WIDTH = 500;
        int MENU_HEIGHT = 720;
        int MENU_LOCATION_X = 0;
        int MENU_LOCATION_Y = 0;

        double MENU_CORNER_ARC_WIDTH = 40.0;
        double MENU_CORNER_ARC_HEIGHT = 40.0;

        Image MENU_ICON = new ImageHandler().getScaledPokedexIcon(ICON_WIDTH, ICON_HEIGHT, "icon","ico.png").getImage();
        ImageIcon BACKGROUND_IMAGE = new ImageHandler().getPokedexImage("bg.gif");

        //  Component handler instantiates and configures all components
        ViewPokemonComponentHandler componentHandler = new ViewPokemonComponentHandler(getViewPokemonMenu(), pokemon, BACKGROUND_IMAGE);
        /*
         *  USING CUSTOM TITLE BAR; proceed to components.TitleBar to change title, icon, and buttons at the top of window.
         */
        //  Set TITLE of JFrame window; components.TitleBar for custom title bar
        getViewPokemonMenu().setTitle("Pokedex");

        //  Set ICON_IMAGE of JFrame window; components.TitleBar for custom title bar
        getViewPokemonMenu().setIconImage(MENU_ICON);

        //  Set SIZE of JFrame window
        getViewPokemonMenu().setSize(MENU_WIDTH, MENU_HEIGHT);

        //  Set JFrame decorated to false; NECESSARY FOR setShape(), DO NOT MODIFY.
        getViewPokemonMenu().setUndecorated(true);

        //  Set SHAPE of JFrame window
        getViewPokemonMenu().setShape(new RoundRectangle2D.Double(
                MENU_LOCATION_X,    //  X coordinate location of this shape
                MENU_LOCATION_Y,    //  Y coordinate location of this shape
                getWidth(),         //  width of this shape
                getHeight(),        //  height of this shape
                MENU_CORNER_ARC_WIDTH,     //  width that rounds off corners
                MENU_CORNER_ARC_HEIGHT     //  height that rounds off corners
        ));

        //  Set LAYOUT of JFrame window
        getViewPokemonMenu().setLayout(new BorderLayout());

        //  Set if JFrame window can be manually resized by the user
        getViewPokemonMenu().setResizable(false);

        //  Set JFrame window to center of user's screen on start
        getViewPokemonMenu().setLocationRelativeTo(null);

        //  REQUIRED set components before setting mainMenu visibility to true
        setUpViewFrame(componentHandler);

        //  Set JFrame window visibility
        getViewPokemonMenu().setVisible(false);

        //  Setting JFrame to focusable activates key listener
        getViewPokemonMenu().setFocusable(true);


    }

    public void setUpViewFrame(ViewPokemonComponentHandler componentHandler) {
        //  Only need to add labelBackground as all other components are already added to the labelBackground
        getViewPokemonMenu().add(componentHandler.getLabelBackground());
    }

    public JFrame getViewPokemonMenu() {
        return viewPokemonMenu;
    }

    public void setViewPokemonMenu(JFrame viewPokemonMenu) {
        this.viewPokemonMenu = viewPokemonMenu;
    }

}
