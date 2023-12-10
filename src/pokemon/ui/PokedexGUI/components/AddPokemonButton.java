package pokemon.ui.PokedexGUI.components;

import pokemon.ui.PokedexGUI.design.MenuGUIDesign;
import pokemon.ui.addPokemonGUI.addPokemonGUI;
import pokemon.util.AudioHandler;
import pokemon.util.FontHandler;
import pokemon.util.ImageHandler;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class AddPokemonButton implements MenuGUIDesign {
    private JButton addPokemonButton;
    private JLabel icon;
    private static final Dimension POKEMONBUTTON_SIZE = (new Dimension(350, 100));
    private static final String FONT_NAME = "pokemonRedBlue.ttf";
    private static final String IMAGE_NAME = "pikachu8bitres.gif";
    private static final float FONT_SIZE = 18f;

    public AddPokemonButton(JFrame menuFrame, AudioHandler mainMenuMusic) throws IOException, FontFormatException {
        ImageIcon IMAGE_ICON = new ImageHandler().getPokedexImage(IMAGE_NAME);

        setIcon(new JLabel(IMAGE_ICON));
        setAddPokemonButton(new JButton(), menuFrame, mainMenuMusic);
    }

    public JButton getAddPokemonButton() {
        return addPokemonButton;
    }

    public JLabel getIcon() {
        return icon;
    }

    public void setAddPokemonButton(JButton addPokemonButton, JFrame frame, AudioHandler mainMenuMusic) throws IOException, FontFormatException {
        Font POKEMONBUTTON_FONT = new FontHandler().getFont(FONT_NAME).deriveFont(FONT_SIZE);

        addPokemonButton.add(getIcon());
        addPokemonButton.setText("ADD POKEMON");
        addPokemonButton.setPreferredSize(POKEMONBUTTON_SIZE);
        addPokemonButton.setFont(POKEMONBUTTON_FONT);
        addPokemonButton.setBorder(null);
        addPokemonButton.setBorderPainted(false);
        addPokemonButton.setFocusPainted(false);
        addPokemonButton.setBackground(AddPokemonButton_COLOR_DEFAULT());
        addPokemonButton.setForeground(AddPokemonButtonText_COLOR_DEFAULT());
        addPokemonButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 0, 0, 10),
                addPokemonButton.getBorder()));

        addPokemonButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                try {
                    addPokemonGUI addPokemonGUI = new addPokemonGUI(frame, mainMenuMusic.getClip());
                    addPokemonGUI.setVisible(true);
                } catch (IOException | ClassNotFoundException | LineUnavailableException |
                         UnsupportedAudioFileException | FontFormatException | CloneNotSupportedException ex) {
                    throw new RuntimeException(ex);
                }
                mainMenuMusic.pause();
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

        this.addPokemonButton = addPokemonButton;
    }

    public void setIcon(JLabel icon) {
            icon.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(0, 10, 0, 20),
                    icon.getBorder()));

        this.icon = icon;
    }
}
