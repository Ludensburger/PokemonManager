package pokemon.ui.ViewPokemonGUI.components;

import pokemon.Pokedex;
import pokemon.Pokemon;
import pokemon.ui.UIRunner;
import pokemon.util.ImageHandler;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ViewPokemonButtonsContainer {

    private JPanel container;
    private JButton backButton;
    private JButton removePokemon;
    private Font font;
    private static Color BACKGROUND_COLOR;
    private static Color FOREGROUND_COLOR;
    private Pokedex pokedex;
    private Pokemon selectedPokemon;
    private final static Dimension BUTTON_SIZE = (new Dimension(200, 30));

    public ViewPokemonButtonsContainer(Font font, Color foregroundColor, Color backgroundColor, Pokedex pokedex, Pokemon selectedPokemon) {
        setPokedex(pokedex);
        setSelectedPokemon(selectedPokemon);
        setFont(font);
        setBackgroundColor(backgroundColor);
        setForegroundColor(foregroundColor);
        setBackButton(new JButton());
        setRemovePokemon(new JButton());
        setContainer(new JPanel());
    }

    public JPanel getContainer() {
        return container;
    }

    public void setContainer(JPanel container) {
        container.setLayout(new FlowLayout());
        container.setOpaque(false);
        container.add(getBackButton(), BorderLayout.EAST);
        container.add(getRemovePokemon(), BorderLayout.WEST);
        this.container = container;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setBackButton(JButton backButton) {
        backButton.setText("BACK");
        backButton.setPreferredSize(BUTTON_SIZE);
        backButton.setForeground(getForegroundColor());
        backButton.setBackground(getBackgroundColor());
        backButton.setFont(getFont());
        backButton.setFocusPainted(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == getBackButton()) {
                    try {
                        UIRunner.getInstance().openPokedexGUI();
                    } catch (LineUnavailableException | IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    UIRunner.getInstance().closeViewPokemonGUI();
                }
            }
        });
        this.backButton = backButton;
    }

    public JButton getRemovePokemon() {
        return removePokemon;
    }

    public void setRemovePokemon(JButton removePokemon) {
        removePokemon.setText("REMOVE");
        removePokemon.setPreferredSize(BUTTON_SIZE);
        removePokemon.setForeground(getForegroundColor());
        removePokemon.setBackground(getBackgroundColor());
        removePokemon.setFont(getFont());
        removePokemon.setFocusPainted(false);

        removePokemon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == getRemovePokemon()) {
                    int option = JOptionPane.showConfirmDialog(null, "Delete pokemon?");

                    if(option == 0) {
                        File imageFile = new ImageHandler().getPokemonImageFile(getSelectedPokemon().getId());
                        boolean deleted = imageFile.delete();
                        if(deleted) {
                            getPokedex().removePokemon(getSelectedPokemon().getId());
                            try {
                                getPokedex().savePokedex();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                            JOptionPane.showMessageDialog(null, "Successfully deleted pokemon!");
                            UIRunner.getInstance().closeViewPokemonGUI();

                            try {
                                UIRunner.getInstance().restartPokedexGUI();
                            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException |
                                     FontFormatException | CloneNotSupportedException | ClassNotFoundException ex) {
                                throw new RuntimeException(ex);
                            }
                        } else {
                            System.out.println("Trouble deleting pokemon!");
                        }
                    }
                }
            }
        });
        this.removePokemon = removePokemon;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public static Color getBackgroundColor() {
        return BACKGROUND_COLOR;
    }

    public static void setBackgroundColor(Color backgroundColor) {
        BACKGROUND_COLOR = backgroundColor;
    }

    public static Color getForegroundColor() {
        return FOREGROUND_COLOR;
    }

    public static void setForegroundColor(Color foregroundColor) {
        FOREGROUND_COLOR = foregroundColor;
    }

    public Pokedex getPokedex() {
        return pokedex;
    }

    public void setPokedex(Pokedex pokedex) {
        this.pokedex = pokedex;
    }

    public Pokemon getSelectedPokemon() {
        return selectedPokemon;
    }

    public void setSelectedPokemon(Pokemon selectedPokemon) {
        this.selectedPokemon = selectedPokemon;
    }
}
