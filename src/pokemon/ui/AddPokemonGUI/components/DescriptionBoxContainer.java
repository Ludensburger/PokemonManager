package pokemon.ui.AddPokemonGUI.components;

import javax.swing.*;
import java.awt.*;

public class DescriptionBoxContainer {
    private JPanel container;
    private JLabel descriptionLabel;
    private JTextArea descriptionTextArea;
    private Font font;
    private static final Dimension LABEL_SIZE = (new Dimension(500, 30));
    private static final Dimension DESCRIPTION_CONTAINER_SIZE = (new Dimension(500, 200));
    private static Color BACKGROUND_COLOR;
    private static  Color FOREGROUND_COLOR;

    public DescriptionBoxContainer(Font font, Color foregroundColor, Color backgroundColor) {
        setFont(font);
        setBackgroundColor(backgroundColor);
        setForegroundColor(foregroundColor);
        setDescriptionLabel(new JLabel());
        setDescriptionTextArea(new JTextArea());
        setContainer(new JPanel());
    }

    public JPanel getContainer() {
        return container;
    }

    public void setContainer(JPanel container) {
        container.setOpaque(false);
        container.setLayout(new BorderLayout());
        container.add(getDescriptionLabel(), BorderLayout.CENTER);
        container.add(getDescriptionTextArea(), BorderLayout.SOUTH);
        this.container = container;
    }

    public JLabel getDescriptionLabel() {
        return descriptionLabel;
    }

    public void setDescriptionLabel(JLabel descriptionLabel) {
        descriptionLabel.setText("DESCRIPTION");
        descriptionLabel.setForeground(getForegroundColor());
        descriptionLabel.setBackground(getBackgroundColor());
        descriptionLabel.setFont(getFont());
        descriptionLabel.setPreferredSize(LABEL_SIZE);
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descriptionLabel.setOpaque(true);
        descriptionLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 0, 10, 0),
                descriptionLabel.getBorder()));
        this.descriptionLabel = descriptionLabel;
    }

    public JTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public void setDescriptionTextArea(JTextArea descriptionTextArea) {
        descriptionTextArea.setPreferredSize(DESCRIPTION_CONTAINER_SIZE);
        descriptionTextArea.setBackground(getForegroundColor());
        descriptionTextArea.setForeground(getBackgroundColor());
        descriptionTextArea.setFont(getFont());
        descriptionTextArea.isEnabled();
        descriptionTextArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 40, 10, 40),
                descriptionTextArea.getBorder()));
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);

        this.descriptionTextArea = descriptionTextArea;
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
