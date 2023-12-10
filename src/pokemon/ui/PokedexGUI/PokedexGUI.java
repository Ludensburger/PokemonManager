package pokemon.ui.PokedexGUI;


import pokemon.Pokedex;
import pokemon.ui.PokedexGUI.actions.HandleKeys;
import pokemon.ui.PokedexGUI.actions.HandleScrollWheel;
import pokemon.ui.PokedexGUI.actions.PanelPainter;
import pokemon.ui.PokedexGUI.components.*;
import pokemon.ui.PokedexGUI.design.MenuGUIDesign;
import pokemon.util.AudioHandler;
import pokemon.util.ImageHandler;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;


public class PokedexGUI extends JFrame implements MenuGUIDesign {
    private JFrame mainMenu;

    public PokedexGUI(Pokedex pokedex) throws CloneNotSupportedException, IOException, FontFormatException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException {
        //  Set mainMenu to this JFrame
        setMainMenu(this);

        int ICON_WIDTH = 64;
        int ICON_HEIGHT = 64;

        int MENU_WIDTH = 1280;
        int MENU_HEIGHT = 720;
        int MENU_LOCATION_X = 0;
        int MENU_LOCATION_Y = 0;

        double MENU_CORNER_ARC_WIDTH = 40.0;
        double MENU_CORNER_ARC_HEIGHT = 40.0;

        Image MENU_ICON = new ImageHandler().getScaledPokedexIcon(ICON_WIDTH, ICON_HEIGHT, "icon","ico.png").getImage();
        ImageIcon BACKGROUND_IMAGE = new ImageHandler().getPokedexImage("bg.gif");

        //  Component handler instantiates and configures all components
        MenuComponentHandler menuComponentHandler = new MenuComponentHandler(getMainMenu(), pokedex, BACKGROUND_IMAGE);
        /*
         *  USING CUSTOM TITLE BAR; proceed to components.TitleBar to change title, icon, and buttons at the top of window.
         */
            //  Set TITLE of JFrame window; components.TitleBar for custom title bar
            getMainMenu().setTitle("Pokedex");

            //  Set ICON_IMAGE of JFrame window; components.TitleBar for custom title bar
            getMainMenu().setIconImage(MENU_ICON);

            //  Set SIZE of JFrame window
            getMainMenu().setSize(MENU_WIDTH, MENU_HEIGHT);

            //  Set JFrame decorated to false; NECESSARY FOR setShape(), DO NOT MODIFY.
            getMainMenu().setUndecorated(true);

            //  Set SHAPE of JFrame window
            getMainMenu().setShape(new RoundRectangle2D.Double(
                    MENU_LOCATION_X,    //  X coordinate location of this shape
                    MENU_LOCATION_Y,    //  Y coordinate location of this shape
                    getWidth(),         //  width of this shape
                    getHeight(),        //  height of this shape
                    MENU_CORNER_ARC_WIDTH,     //  width that rounds off corners
                    MENU_CORNER_ARC_HEIGHT     //  height that rounds off corners
            ));

            //  Set LAYOUT of JFrame window
            getMainMenu().setLayout(new BorderLayout());

            //  Set if JFrame window can be manually resized by the user
            getMainMenu().setResizable(false);

            //  Set JFrame window to center of user's screen on start
            getMainMenu().setLocationRelativeTo(null);

            //  REQUIRED set components before setting mainMenu visibility to true
            setupMainFrame(menuComponentHandler);

            //  Set JFrame window visibility
            getMainMenu().setVisible(true);

            //  Setting JFrame to focusable activates key listener
            getMainMenu().setFocusable(true);

            getMainMenu().addKeyListener(addKeyEvent(menuComponentHandler));
            getMainMenu().addMouseWheelListener(addScrollWheelEvent());
    }

    public void setupMainFrame(MenuComponentHandler menuComponentHandler) {
        //  Only need to add labelBackground as all other components are already added to the labelBackground
        getMainMenu().add(menuComponentHandler.getLabelBackground());
    }


    public MouseWheelListener addScrollWheelEvent() {
        HandleScrollWheel handleScrollWheel = new HandleScrollWheel(PanelPainter.getInstance());

        return handleScrollWheel.addHandleScrollWheel();
    }

    public KeyAdapter addKeyEvent(MenuComponentHandler menuComponentHandler) {
        AudioHandler mainMenuMusic = menuComponentHandler.getMainMenuMusic();
        HandleKeys handleArrowKeys = new HandleKeys(PanelPainter.getInstance(), getMainMenu(), mainMenuMusic);

        return handleArrowKeys.addHandleKeys();
    }


    public JFrame getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(JFrame mainMenu) {
        this.mainMenu = mainMenu;
    }

}
