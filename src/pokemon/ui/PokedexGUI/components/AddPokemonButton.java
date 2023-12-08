package pokemon.ui.PokedexGUI.components;

import pokemon.ui.PokedexGUI.design.MenuDesign;
import pokemon.ui.addPokemonGUI.addPokemonGUI;
import pokemon.util.FontHandler;
import pokemon.util.ImageHandler;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class AddPokemonButton implements MenuDesign {
    private final JButton addPokemonButton;
    private final JLabel icon;

    public AddPokemonButton(JFrame frame, Clip music) throws IOException, FontFormatException {
        ImageIcon IMAGE_ICON = new ImageHandler().getPokedexImage("pikachu8bitres.gif");

        this.addPokemonButton = new JButton();
        this.icon = new JLabel(IMAGE_ICON);

        decorateAddButton();

        getAddPokemonButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                try {
                    addPokemonGUI addPokemon = new addPokemonGUI(frame, music);
                    addPokemon.setVisible(true);
                } catch (IOException | ClassNotFoundException | LineUnavailableException |
                         UnsupportedAudioFileException | FontFormatException | CloneNotSupportedException ex) {
                    throw new RuntimeException(ex);
                }
                music.stop();
                frame.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e){
                super.mouseEntered(e);
                getAddPokemonButton().setBackground(AddPokemonButton_COLOR_SELECTED());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                getAddPokemonButton().setBackground(AddPokemonButton_COLOR_DEFAULT());
            }
        });

    }

    public JButton getAddPokemonButton() {
        return addPokemonButton;
    }

    public JLabel getIcon() {
        return icon;
    }


    public void decorateAddButton() throws IOException, FontFormatException {
        //  Getting font
        Font FONT = new FontHandler().getFont("pokemonRedBlue.ttf").deriveFont(18f);
        getIcon().setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 10, 0, 20),
                getAddPokemonButton().getBorder()));

        // Button
        getAddPokemonButton().add(getIcon());
        getAddPokemonButton().setPreferredSize(AddPokemonButton_SIZE_DEFAULT());
        getAddPokemonButton().setText("ADD POKEMON");
        getAddPokemonButton().setBackground(AddPokemonButton_COLOR_DEFAULT());
        getAddPokemonButton().setPreferredSize(AddPokemonButton_SIZE_DEFAULT());
        getAddPokemonButton().setFont(FONT);
        getAddPokemonButton().setBorder(null);
        getAddPokemonButton().setBorderPainted(false);
        getAddPokemonButton().setFocusPainted(false);
        getAddPokemonButton().setForeground(AddPokemonButtonText_COLOR_DEFAULT());
        getAddPokemonButton().setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 0, 0, 10),
                getAddPokemonButton().getBorder()));
    }
}
