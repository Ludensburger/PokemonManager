package pokemon.ui.AddPokemonGUI.components;

import javax.swing.*;
import java.awt.*;

public class NameContainer {

    private JPanel container;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private Font font;
    private static Color BACKGROUND_COLOR;
    private static Color FOREGROUND_COLOR;
    private static final Dimension LABEL_SIZE = (new Dimension(150, 100));
    private static final Dimension TEXTFIELD_SIZE = (new Dimension(350, 100));
    private static final Dimension NAME_CONTAINER_SIZE = (new Dimension(200, 50));

    public NameContainer(Font font, Color foregroundColor, Color backgroundColor) {
        setFont(font);
        setBackgroundColor(backgroundColor);
        setForegroundColor(foregroundColor);
        setNameLabel(new JLabel());
        setNameTextField(new JTextField());
        setContainer(new JPanel());
    }

    public JPanel getContainer() {
        return container;
    }

    public void setContainer(JPanel container) {
        container.setPreferredSize(NAME_CONTAINER_SIZE);
        container.setBackground(BACKGROUND_COLOR);
        container.setLayout(new BorderLayout());
        container.add(getNameLabel(), BorderLayout.WEST);
        container.add(getNameTextField(), BorderLayout.EAST);
        this.container = container;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(JLabel nameLabel) {
        nameLabel.setText("NAME");
        nameLabel.setFont(getFont());
        nameLabel.setForeground(FOREGROUND_COLOR);
        nameLabel.setPreferredSize(LABEL_SIZE);
        nameLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 20, 0, 20),
                nameLabel.getBorder()));
        this.nameLabel = nameLabel;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(JTextField nameTextField) {
        nameTextField.setFont(getFont());
        nameTextField.setPreferredSize(TEXTFIELD_SIZE);
        nameTextField.setForeground(BACKGROUND_COLOR);
        nameTextField.setBackground(FOREGROUND_COLOR);
        nameTextField.setBorder(null);
        nameTextField.isEnabled();
        nameTextField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 20, 0, 20),
                nameTextField.getBorder()));
        this.nameTextField = nameTextField;
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
