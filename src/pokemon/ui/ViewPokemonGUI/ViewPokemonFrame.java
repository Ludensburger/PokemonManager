package pokemon.ui.ViewPokemonGUI;

import pokemon.Pokedex;
import pokemon.Pokemon;
import pokemon.ui.ViewPokemonGUI.components.ViewPokemonComponentHandler;
import pokemon.ui.components.Frame.FrameModel;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

public class ViewPokemonFrame extends FrameModel {

    private ViewPokemonComponentHandler componentHandler;

    public ViewPokemonFrame() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        super(500, 720);

        getFrame().setLocationRelativeTo(null);

    }

    public void setUpViewFrame(Pokemon pokemon, Pokedex pokedex) throws UnsupportedAudioFileException, LineUnavailableException, IOException, FontFormatException {
        //  Only need to add labelBackground as all other components are already added to the labelBackground
        if(getComponentHandler() == null) {
            ViewPokemonComponentHandler componentHandler = new ViewPokemonComponentHandler(
                    pokedex,
                    getFrame(),
                    pokemon,
                    getFrameBackgroundImage()
            );
            setComponentHandler(componentHandler);
        } else {
            getFrame().remove(getComponentHandler().getLabelBackground());
            getComponentHandler().setSelectedPokemon(pokemon);
            ViewPokemonComponentHandler cloneHandler = getComponentHandler().clone();
            setComponentHandler(cloneHandler);
        }

        getFrame().add(getComponentHandler().getLabelBackground());
    }

    public ViewPokemonComponentHandler getComponentHandler() {
        return componentHandler;
    }

    public void setComponentHandler(ViewPokemonComponentHandler componentHandler) {
        this.componentHandler = componentHandler;
    }
}
