package pokemon.ui.PokedexGUI.actions;

import pokemon.Pokedex;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;

public class HandleScrollWheel {

        private int WHEEL_ROTATION;

        private PanelPainter panelPainter;

        private int MAX_POKEMON;

        private int MAX_POKEMON_PANELS;

    public HandleScrollWheel(PanelPainter panelPainter) {
        setPanelPainter(panelPainter);
        setMAX_POKEMON(panelPainter.getPokedex().getPokemonHashMap().size());
        setMAX_POKEMON_PANELS(5);
    }

    public MouseWheelListener addHandleScrollWheel() {
        return new MouseWheelListener() {
            private long lastEventTime = 0;
            private static final long COOLDOWN_PERIOD = 100;

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                long currentTime = System.currentTimeMillis();

                if (currentTime - lastEventTime < COOLDOWN_PERIOD) {
                    return;
                }
                lastEventTime = currentTime;

                setWheelRotation(e.getWheelRotation());

                if (getWheelRotation() > 0) {
                    getPanelPainter().getNavigatorInstance().moveDown(getMAX_POKEMON(), getMAX_POKEMON_PANELS());
                } else {
                    getPanelPainter().getNavigatorInstance().moveUp();
                }

                try {
                    getPanelPainter().paintPanels();
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }


    public int getWheelRotation() {
        return WHEEL_ROTATION;
    }

    public void setWheelRotation(int wheelRotation) {
        WHEEL_ROTATION = wheelRotation;
    }

    public PanelPainter getPanelPainter() {
        return panelPainter;
    }

    public void setPanelPainter(PanelPainter panelPainter) {
        this.panelPainter = panelPainter;
    }

    public int getMAX_POKEMON() {
        return MAX_POKEMON;
    }

    public void setMAX_POKEMON(int MAX_POKEMON) {
        this.MAX_POKEMON = MAX_POKEMON;
    }

    public int getMAX_POKEMON_PANELS() {
        return MAX_POKEMON_PANELS;
    }

    public void setMAX_POKEMON_PANELS(int MAX_POKEMON_PANELS) {
        this.MAX_POKEMON_PANELS = MAX_POKEMON_PANELS;
    }
}
