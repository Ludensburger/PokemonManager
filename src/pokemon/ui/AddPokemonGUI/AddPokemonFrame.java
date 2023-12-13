package pokemon.ui.AddPokemonGUI;

import pokemon.Pokedex;
import pokemon.ui.AddPokemonGUI.components.AddPokemonComponentHandler;
import pokemon.ui.ViewPokemonGUI.components.ViewPokemonComponentHandler;
import pokemon.ui.components.Frame.FrameModel;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

public class AddPokemonFrame extends FrameModel {

    private Pokedex pokedex;
    public AddPokemonFrame(Pokedex pokedex) throws IOException, UnsupportedAudioFileException, LineUnavailableException, FontFormatException {
        super(500, 720);

        setPokedex(pokedex);
        getFrame().setLocationRelativeTo(null);

        AddPokemonComponentHandler componentHandler = new AddPokemonComponentHandler(
                getFrame(),
                getPokedex(),
                getFrameBackgroundImage()
        );

        setUpAddPokemonFrame(componentHandler);
    }

    public void setUpAddPokemonFrame(AddPokemonComponentHandler componentHandler) {
        //  Only need to add labelBackground as all other components are already added to the labelBackground
        getFrame().add(componentHandler.getLabelBackground());
    }

    public Pokedex getPokedex() {
        return pokedex;
    }

    public void setPokedex(Pokedex pokedex) {
        this.pokedex = pokedex;
    }
}
