package pokemon.ui.PokedexGUI;

import pokemon.Pokedex;
import pokemon.ui.PokedexGUI.actions.HandleKeys;
import pokemon.ui.PokedexGUI.actions.HandleScrollWheel;
import pokemon.ui.PokedexGUI.actions.PanelPainter;
import pokemon.ui.PokedexGUI.components.MenuComponentHandler;
import pokemon.ui.components.Frame.FrameModel;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseWheelListener;
import java.io.IOException;

public class PokedexFrame extends FrameModel {
    public PokedexFrame(Pokedex pokedex) throws IOException, UnsupportedAudioFileException, LineUnavailableException, FontFormatException, CloneNotSupportedException {
        super(1280, 720);

        MenuComponentHandler menuComponentHandler = new MenuComponentHandler(
                getFrame(),
                pokedex,
                getFrameBackgroundImage(),
                getFrameMusic()
        );

        setupMainFrame(menuComponentHandler);

        getFrame().addKeyListener(addKeyEvent(menuComponentHandler));
        getFrame().addMouseWheelListener(addScrollWheelEvent());
    }

    public void setupMainFrame(MenuComponentHandler menuComponentHandler) {
        //  Only need to add labelBackground as all other components are already added to the labelBackground
        getFrame().add(menuComponentHandler.getLabelBackground());
    }

    public MouseWheelListener addScrollWheelEvent() {
        HandleScrollWheel handleScrollWheel = new HandleScrollWheel(PanelPainter.getInstance());

        return handleScrollWheel.addHandleScrollWheel();
    }

    public KeyAdapter addKeyEvent(MenuComponentHandler menuComponentHandler) {
        HandleKeys handleArrowKeys = new HandleKeys(PanelPainter.getInstance(), getFrame(), getFrameMusic());

        return handleArrowKeys.addHandleKeys();
    }
}
