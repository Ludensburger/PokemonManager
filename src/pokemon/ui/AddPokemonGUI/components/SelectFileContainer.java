package pokemon.ui.AddPokemonGUI.components;

import pokemon.Pokedex;
import pokemon.util.ImageHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class SelectFileContainer {

    private JPanel container;
    private JButton selectFileButton;
    private JLabel imageLabel;
    private JPanel bodyContainer;
    private Font font;
    private int pokemonId;
    private static final Dimension BUTTON_SIZE = (new Dimension(200, 100));
    private static Color BACKGROUND_COLOR;
    private static Color FOREGROUND_COLOR;

    public SelectFileContainer(Font font, Color foregroundColor, Color backgroundColor, int pokemonId, JPanel bodyContainer) {
        setFont(font);
        setPokemonId(pokemonId);
        setBodyContainer(bodyContainer);
        setImageLabel(new JLabel());
        setBackgroundColor(backgroundColor);
        setForegroundColor(foregroundColor);
        setSelectFileButton(new JButton());
        setContainer(new JPanel());
    }

    public JPanel getContainer() {
        return container;
    }

    public void setContainer(JPanel container) {
        container.setLayout(new BorderLayout());
        container.add(getSelectFileButton(), BorderLayout.NORTH);
        container.setOpaque(false);
        container.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 0, 0, 0),
                container.getBorder()));


        this.container = container;
    }

    public JButton getSelectFileButton() {
        return selectFileButton;
    }

    public void setSelectFileButton(JButton selectFileButton) {
        selectFileButton.setText("SELECT FILE");
        selectFileButton.setFont(getFont());
        selectFileButton.setBackground(BACKGROUND_COLOR);
        selectFileButton.setForeground(FOREGROUND_COLOR);
        selectFileButton.setPreferredSize(BUTTON_SIZE);
        selectFileButton.setFocusPainted(false);
        selectFileButton.setHorizontalTextPosition(SwingConstants.CENTER);
        selectFileButton.setHorizontalAlignment(SwingConstants.CENTER);
        this.selectFileButton = selectFileButton;
    }

    public JLabel getImageLabel() {
        return imageLabel;
    }

    public void setImageLabel(JLabel imageLabel) {
        this.imageLabel = imageLabel;
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

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public JPanel getBodyContainer() {
        return bodyContainer;
    }

    public void setBodyContainer(JPanel bodyContainer) {
        this.bodyContainer = bodyContainer;
    }
}
