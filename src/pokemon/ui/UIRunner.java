package pokemon.ui;

import java.awt.*;
import java.io.IOException;

import pokemon.*;
import pokemon.ui.AddPokemonGUI.AddPokemonFrame;
import pokemon.ui.PokedexGUI.PokedexFrame;
import pokemon.ui.PokedexGUI.actions.PanelNavigator;
import pokemon.ui.ViewPokemonGUI.ViewPokemonFrame;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *  Used for running JFrames
 */
public class UIRunner {

    private static UIRunner instance;
    private PokedexFrame PokedexGUI;
    private Pokedex pokedex;
    private ViewPokemonFrame ViewPokemonGUI;
    private AddPokemonFrame AddPokemonGUI;
    private UIRunner() {}

    public static UIRunner getInstance() {
        if (instance == null) {
            instance = new UIRunner();
        }
        return instance;
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, FontFormatException, ClassNotFoundException, CloneNotSupportedException {
        UIRunner uiRunner = new UIRunner();
        setInstance(uiRunner);
        uiRunner.startPokedexGUI();
    }

    public void startPokedexGUI() throws UnsupportedAudioFileException, LineUnavailableException, IOException, FontFormatException, ClassNotFoundException, CloneNotSupportedException {
        Pokedex pokedex = new Pokedex();
        pokedex.startPokedex();
        setPokedex(pokedex);
        PokedexFrame pokedexGUI = new PokedexFrame(getPokedex());
        AddPokemonFrame addPokemonGUI = new AddPokemonFrame(getPokedex());
        ViewPokemonFrame viewPokemonFrame = new ViewPokemonFrame();

        setPokedexGUI(pokedexGUI);
        setAddPokemonGUI(addPokemonGUI);
        setViewPokemonGUI(viewPokemonFrame);

        openPokedexGUI();
    }

    public void openPokedexGUI() throws LineUnavailableException, IOException {
        getPokedexGUI().getFrameMusic().play(Clip.LOOP_CONTINUOUSLY);
        getPokedexGUI().setVisible(true);
    }

    public void restartPokedexGUI() throws UnsupportedAudioFileException, LineUnavailableException, IOException, FontFormatException, CloneNotSupportedException, ClassNotFoundException {
        getPokedexGUI().dispose();
        Pokedex pokedex = new Pokedex();
        pokedex.startPokedex();
        setPokedex(pokedex);
        setPokedexGUI(new PokedexFrame(pokedex));
        openPokedexGUI();
    }

    public void openViewPokemonGUI(Pokemon pokemon) throws LineUnavailableException, IOException, UnsupportedAudioFileException, FontFormatException {
        getViewPokemonGUI().setUpViewFrame(pokemon, getPokedex());
        getViewPokemonGUI().getFrameMusic().play(Clip.LOOP_CONTINUOUSLY);
        getViewPokemonGUI().setVisible(true);
    }


    public void openAddPokemonGUI() throws LineUnavailableException, IOException {
        getAddPokemonGUI().getFrameMusic().play(Clip.LOOP_CONTINUOUSLY);
        getAddPokemonGUI().setVisible(true);
    }

    public void closePokedexGUI() {
        getPokedexGUI().setVisible(false);
        getPokedexGUI().getFrameMusic().pause();
    }

    public void closeViewPokemonGUI() {
        getViewPokemonGUI().setVisible(false);
        getViewPokemonGUI().getFrameMusic().pause();
    }

    public void closeAddPokemonGUI() {
        getAddPokemonGUI().setVisible(false);
        getAddPokemonGUI().getFrameMusic().pause();
    }

    public PokedexFrame getPokedexGUI() {
        return PokedexGUI;
    }

    public void setPokedexGUI(PokedexFrame pokedexGUI) {
        PokedexGUI = pokedexGUI;
    }

    public ViewPokemonFrame getViewPokemonGUI() {
        return ViewPokemonGUI;
    }

    public void setViewPokemonGUI(ViewPokemonFrame viewPokemonGUI) {
        ViewPokemonGUI = viewPokemonGUI;
    }

    public AddPokemonFrame getAddPokemonGUI() {
        return AddPokemonGUI;
    }

    public void setAddPokemonGUI(AddPokemonFrame addPokemonGUI) {
        AddPokemonGUI = addPokemonGUI;
    }

    public static void setInstance(UIRunner instance) {
        UIRunner.instance = instance;
    }

    public Pokedex getPokedex() {
        return pokedex;
    }

    public void setPokedex(Pokedex pokedex) {
        this.pokedex = pokedex;
    }
}
