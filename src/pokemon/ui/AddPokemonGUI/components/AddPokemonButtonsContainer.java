package pokemon.ui.AddPokemonGUI.components;

import javax.swing.*;
import java.awt.*;

public class AddPokemonButtonsContainer {

    private JPanel container;
    private JButton cancelButton;
    private JButton savePokemon;
    private Font font;
    private static Color BACKGROUND_COLOR;
    private static Color FOREGROUND_COLOR;
    private final static Dimension BUTTON_SIZE = (new Dimension(200, 30));

    public AddPokemonButtonsContainer(Font font, Color foregroundColor, Color backgroundColor) {
        setFont(font);
        setBackgroundColor(backgroundColor);
        setForegroundColor(foregroundColor);
        setCancelButton(new JButton());
        setSavePokemon(new JButton());
        setContainer(new JPanel());
    }

    public JPanel getContainer() {
        return container;
    }

    public void setContainer(JPanel container) {
        container.setLayout(new FlowLayout());
        container.setOpaque(false);
        container.add(getCancelButton(), BorderLayout.EAST);
        container.add(getSavePokemon(), BorderLayout.WEST);
        this.container = container;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        cancelButton.setText("CANCEL");
        cancelButton.setPreferredSize(BUTTON_SIZE);
        cancelButton.setForeground(getForegroundColor());
        cancelButton.setBackground(getBackgroundColor());
        cancelButton.setFont(getFont());
        cancelButton.setFocusPainted(false);
        this.cancelButton = cancelButton;
    }

    public JButton getSavePokemon() {
        return savePokemon;
    }

    public void setSavePokemon(JButton savePokemon) {
        savePokemon.setText("SAVE");
        savePokemon.setPreferredSize(BUTTON_SIZE);
        savePokemon.setForeground(getForegroundColor());
        savePokemon.setBackground(getBackgroundColor());
        savePokemon.setFont(getFont());
        savePokemon.setFocusPainted(false);
        this.savePokemon = savePokemon;
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
}
