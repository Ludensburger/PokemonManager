package pokemon.ui.addPokemonGUI;

import pokemon.Pokedex;
import pokemon.ui.addPokemonGUI.components.TitleBar;
import pokemon.ui.addPokemonGUI.design.AddPokemonDesign;
import pokemon.util.AudioHandler;
import pokemon.util.ImageHandler;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;

public class addPokemonGUI extends JFrame implements AddPokemonDesign {

    private Pokedex pokedex;

    private JPanel titlePanel;

    private JFrame mainMenu;

    private Clip mainMenuMusic;

    private JLabel labelBackground;

    private JPanel headerContainer;

    public addPokemonGUI(JFrame menu, Clip mainMenuMusic) throws IOException, ClassNotFoundException, LineUnavailableException, UnsupportedAudioFileException, FontFormatException, CloneNotSupportedException {

        new AudioHandler("rustborocity.wav").play(-1);

        //  Gets Icon
        ImageIcon ICON = new ImageHandler().getScaledPokedexIcon(64, 64, "icon","ico.png");

        //  Sets background image
        ImageIcon BACKGROUND = new ImageHandler().getPokedexImage("bg.gif");
        setLabelBackground(new JLabel(BACKGROUND));

        //  Starts Pokedex
        setPokedex(new Pokedex());
        getPokedex().startPokedex();

        this.setTitle("Add Pokemon");
        this.setIconImage(ICON.getImage());
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 720);
        this.setUndecorated(true);
        this.setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 40, 40));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.add(getLabelBackground());


        setMainMenu(menu);
        setMainMenuMusic(mainMenuMusic);
        setHeaderContainer(new JPanel());
        setTitlePanel(new TitleBar(this, getMainMenu(), getMainMenuMusic(), new AudioHandler("selectSoundEffect.wav").getClip()).getTitlePanel());
        loadComponents();
    }
    private void loadComponents() throws CloneNotSupportedException, IOException, FontFormatException {
        getLabelBackground().setLayout(new BorderLayout());

        getHeaderContainer().setLayout(new BorderLayout());
        getHeaderContainer().add(getTitlePanel(), BorderLayout.NORTH);

        getLabelBackground().add(getHeaderContainer(), BorderLayout.NORTH);
    }


    public Pokedex getPokedex() {
        return pokedex;
    }

    public void setPokedex(Pokedex pokedex) {
        this.pokedex = pokedex;
    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(JPanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    public JLabel getLabelBackground() {
        return labelBackground;
    }

    public void setLabelBackground(JLabel labelBackground) {
        this.labelBackground = labelBackground;
    }

    public JPanel getHeaderContainer() {
        return headerContainer;
    }

    public void setHeaderContainer(JPanel headerContainer) {
        this.headerContainer = headerContainer;
    }

    public JFrame getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(JFrame mainMenu) {
        this.mainMenu = mainMenu;
    }
    public Clip getMainMenuMusic() {
        return mainMenuMusic;
    }

    public void setMainMenuMusic(Clip mainMenuMusic) {
        this.mainMenuMusic = mainMenuMusic;
    }
}
