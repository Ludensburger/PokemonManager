package pokemon.ui.AddPokemonGUI.components;

import pokemon.PokemonType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TypeComboBox {

    private JPanel container;
    private JComboBox<PokemonType> primaryTypeJComboBox;
    private JComboBox<PokemonType> secondaryTypeJComboBox;
    private JButton addType;
    private JButton removeType;
    private Font font;
    private static final Dimension COMBO_BOX_SIZE = (new Dimension(200, 50));
    private static final Dimension ADD_BUTTON_SIZE = (new Dimension(200, 50));
    private static final Dimension REMOVE_BUTTON_SIZE = (new Dimension(200, 50));
    private static final Color BACKGROUND_COLOR = Color.black;
    private static final Color FOREGROUND_COLOR = Color.white;
    public TypeComboBox(JPanel container, Font font) {
        setFont(font);
        setAddType(new JButton());
        setPrimaryTypeJComboBox(new JComboBox<>());
        setSecondaryTypeJComboBox(new JComboBox<>());
        setAddType(new JButton());
        setRemoveType(new JButton());
        setContainer(container);
    }

    private JComboBox<PokemonType> getJComboBoxModel() {
        JComboBox<PokemonType> comboBox = new JComboBox<>(PokemonType.values());
        DefaultListCellRenderer centerAlign = new DefaultListCellRenderer();
        centerAlign.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

        comboBox.setPreferredSize(COMBO_BOX_SIZE);
        comboBox.setBackground(BACKGROUND_COLOR);
        comboBox.setForeground(FOREGROUND_COLOR);
        comboBox.setFont(getFont().deriveFont(12f));
        comboBox.setFocusable(false);
        comboBox.setRenderer(centerAlign);

        return comboBox;
    }

    public JPanel getContainer() {
        return container;
    }

    public void setContainer(JPanel container) {
            container.setLayout(new FlowLayout());
            container.setOpaque(false);
            container.add(getPrimaryTypeJComboBox());
            container.add(getAddType());
            container.add(getSecondaryTypeJComboBox());
            container.add(getRemoveType());

        this.container = container;
    }

    public JComboBox<PokemonType> getPrimaryTypeJComboBox() {
        return primaryTypeJComboBox;
    }

    public void setPrimaryTypeJComboBox(JComboBox<PokemonType> primaryTypeJComboBox) {
            primaryTypeJComboBox = getJComboBoxModel();

        this.primaryTypeJComboBox = primaryTypeJComboBox;
    }

    public JComboBox<PokemonType> getSecondaryTypeJComboBox() {
        return secondaryTypeJComboBox;
    }

    public void setSecondaryTypeJComboBox(JComboBox<PokemonType> secondaryTypeJComboBox) {
            secondaryTypeJComboBox = getJComboBoxModel();
            secondaryTypeJComboBox.setVisible(false);

        this.secondaryTypeJComboBox = secondaryTypeJComboBox;
    }

    public JButton getAddType() {
        return addType;
    }

    public void setAddType(JButton addType) {
            addType.setText("ADD TYPE");
            addType.setPreferredSize(ADD_BUTTON_SIZE);
            addType.setBackground(BACKGROUND_COLOR);
            addType.setForeground(FOREGROUND_COLOR);
            addType.setFont(getFont().deriveFont(12f));
            addType.setFocusPainted(false);
            addType.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    addType.setVisible(false);
                    getSecondaryTypeJComboBox().setVisible(true);
                    getRemoveType().setVisible(true);
            }
            });

        this.addType = addType;
    }

    public JButton getRemoveType() {
        return removeType;
    }

    public void setRemoveType(JButton removeType) {
            removeType.setText("REMOVE TYPE");
            removeType.setPreferredSize(REMOVE_BUTTON_SIZE);
            removeType.setBackground(BACKGROUND_COLOR);
            removeType.setForeground(FOREGROUND_COLOR);
            removeType.setFont(getFont().deriveFont(12f));
            removeType.setFocusPainted(false);
            removeType.setVisible(false);
            removeType.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    removeType.setVisible(false);
                    getSecondaryTypeJComboBox().setVisible(false);
                    getAddType().setVisible(true);
            }
            });
        this.removeType = removeType;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
