package pokemon.ui.PokedexGUI.components;

import pokemon.ui.UIRunner;
import pokemon.util.AudioHandler;
import pokemon.util.FontHandler;
import pokemon.util.ImageHandler;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class AddPokemonButton {
    private JButton addPokemonButton;
    private JLabel icon;
    private int iconWidth = 64;
    private int iconHeight = 64;
    private static final Dimension POKEMONBUTTON_SIZE = (new Dimension(350, 100));
    private static final String FONT_NAME = "pokemonRedBlue.ttf";
    private static final String IMAGE_NAME = "pikachu8bitres.gif";
    private static final float FONT_SIZE = 18f;
    private static final Color BUTTON_COLOR_DEFAULT = Color.black;
    private static final Color BUTTON_COLOR_SELECTED = Color.red;
    private static final Color TEXT_COLOR_DEFAULT = Color.white;

    public AddPokemonButton(JFrame menuFrame, AudioHandler mainMenuMusic) throws IOException, FontFormatException {
        ImageIcon IMAGE_ICON = new ImageHandler().getPokedexImage(getIconWidth(), getIconHeight(), IMAGE_NAME, true);

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
        Font POKEMONBUTTON_FONT = new FontHandler().getFont(FONT_NAME, FONT_SIZE);

        addPokemonButton.add(getIcon());
        addPokemonButton.setText("ADD POKEMON");
        addPokemonButton.setPreferredSize(POKEMONBUTTON_SIZE);
        addPokemonButton.setFont(POKEMONBUTTON_FONT);
        addPokemonButton.setBorder(null);
        addPokemonButton.setBorderPainted(false);
        addPokemonButton.setFocusPainted(false);
        addPokemonButton.setBackground(BUTTON_COLOR_DEFAULT);
        addPokemonButton.setForeground(TEXT_COLOR_DEFAULT);
        addPokemonButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 0, 0, 10),
                addPokemonButton.getBorder()));

        addPokemonButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                try {
                    UIRunner.getInstance().openAddPokemonGUI();
                    UIRunner.getInstance().closePokedexGUI();
                } catch (IOException | LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e){
                super.mouseEntered(e);
                getAddPokemonButton().setBackground(BUTTON_COLOR_SELECTED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                getAddPokemonButton().setBackground(BUTTON_COLOR_DEFAULT);
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

    public int getIconWidth() {
        return iconWidth;
    }

    public void setIconWidth(int iconWidth) {
        this.iconWidth = iconWidth;
    }

    public int getIconHeight() {
        return iconHeight;
    }

    public void setIconHeight(int iconHeight) {
        this.iconHeight = iconHeight;
    }
}
