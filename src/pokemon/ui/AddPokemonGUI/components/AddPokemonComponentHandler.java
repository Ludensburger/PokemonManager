package pokemon.ui.AddPokemonGUI.components;

import pokemon.Pokedex;
import pokemon.PokemonType;
import pokemon.util.FontHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class AddPokemonComponentHandler {
    private JLabel labelBackground;
    private JPanel titleBarContainer;
    private JPanel bodyContainer;
    private JPanel pokemonNameContainer;
    private JPanel pokemonImageContainer;
    private JPanel pokemonTypeContainer;
    private JPanel pokemonDescriptionContainer;
    private JTextField pokemonName;
    private PokemonType primaryType;
    private PokemonType secondaryType;
    private JTextArea pokemonDescription;
    Pokedex pokedex;
    Font font;
    private static final String FONT_NAME = "pokemonRedBlue.ttf";
    private static final float FONT_SIZE = 16f;
    private static final Color BACKGROUND_COLOR = Color.black;
    private static final Color FOREGROUND_COLOR = Color.white;
    private static final Dimension NAME_CONTAINER_SIZE = (new Dimension(200, 50));
    private static final Dimension TYPE_CONTAINER_SIZE = (new Dimension(300, 200));

    private static final Dimension DESCRIPTION_CONTAINER_SIZE = (new Dimension(500, 200));




    public AddPokemonComponentHandler(JFrame frame, Pokedex pokedex, ImageIcon backgroundImage) throws IOException, FontFormatException {
        setFont(new FontHandler().getFont(FONT_NAME, FONT_SIZE));
        setPokedex(pokedex);
        setPokemonNameContainer(new JPanel());
        setPokemonTypeContainer(new JPanel());
        setPokemonDescription(new JTextArea());
        setBodyContainer(new JPanel());
        setTitleBarContainer(new AddPokemonTitleBar(frame).getTitlePanel());
        setLabelBackground(new JLabel(backgroundImage));
    }

    public JPanel getTitleBarContainer() {
        return titleBarContainer;
    }

    public void setTitleBarContainer(JPanel titleBarContainer) {
        this.titleBarContainer = titleBarContainer;
    }

    public JLabel getLabelBackground() {
        return labelBackground;
    }

    public void setLabelBackground(JLabel labelBackground) {
            labelBackground.setLayout(new BorderLayout());
            labelBackground.add(getTitleBarContainer(), BorderLayout.NORTH);
            labelBackground.add(getBodyContainer(), BorderLayout.CENTER);

        this.labelBackground = labelBackground;
    }

    public JPanel getBodyContainer() {
        return bodyContainer;
    }

    public void setBodyContainer(JPanel bodyContainer) {
        bodyContainer.setLayout(new BorderLayout());
        bodyContainer.setOpaque(false);
        bodyContainer.add(getPokemonNameContainer(), BorderLayout.NORTH);
//            bodyContainer.add(getPokemonImageContainer(), BorderLayout.WEST);
        bodyContainer.add(getPokemonTypeContainer(),  BorderLayout.EAST);
        bodyContainer.add(getPokemonDescription(), BorderLayout.SOUTH);
        bodyContainer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20),
                bodyContainer.getBorder()));

        this.bodyContainer = bodyContainer;
    }

    public JPanel getPokemonNameContainer() {
        return pokemonNameContainer;
    }

    public void setPokemonNameContainer(JPanel pokemonNameContainer) {
        Dimension LABEL_SIZE = new Dimension(200, 100);
        JLabel label = new JLabel("NAME");

            label.setFont(getFont());
            label.setForeground(FOREGROUND_COLOR);
            label.setPreferredSize(LABEL_SIZE);
            label.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(0, 20, 0, 20),
                    label.getBorder()));

        Dimension NAME_SIZE = new Dimension(350, 100);
        JTextField name = new JTextField();

            name.setFont(getFont());
            name.setPreferredSize(NAME_SIZE);
            name.setForeground(BACKGROUND_COLOR);
            name.setBackground(FOREGROUND_COLOR);
            name.setBorder(null);
            name.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(0, 20, 0, 20),
                    name.getBorder()));

            pokemonNameContainer.setPreferredSize(NAME_CONTAINER_SIZE);
            pokemonNameContainer.setBackground(BACKGROUND_COLOR);
            pokemonNameContainer.setLayout(new BorderLayout());
            pokemonNameContainer.add(label, BorderLayout.WEST);
            pokemonNameContainer.add(name, BorderLayout.EAST);

        setPokemonName(name);
        this.pokemonNameContainer = pokemonNameContainer;
    }

    public JPanel getPokemonImageContainer() {
        return pokemonImageContainer;
    }

    public void setPokemonImageContainer(JPanel pokemonImageContainer) {
        this.pokemonImageContainer = pokemonImageContainer;
    }

    public JPanel getPokemonTypeContainer() {
        return pokemonTypeContainer;
    }

    public void setPokemonTypeContainer(JPanel pokemonTypeContainer) {
        TypeComboBox comboBox = new TypeComboBox(pokemonTypeContainer, getFont());

        comboBox.getContainer().setPreferredSize(TYPE_CONTAINER_SIZE);
        this.pokemonTypeContainer = pokemonTypeContainer;
    }

    public Pokedex getPokedex() {
        return pokedex;
    }

    public void setPokedex(Pokedex pokedex) {
        this.pokedex = pokedex;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public JTextField getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(JTextField pokemonName) {
        this.pokemonName = pokemonName;
    }

    public PokemonType getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(PokemonType primaryType) {
        this.primaryType = primaryType;
    }

    public PokemonType getSecondaryType() {
        return secondaryType;
    }

    public void setSecondaryType(PokemonType secondaryType) {
        this.secondaryType = secondaryType;
    }

    public JTextArea getPokemonDescription() {
        return pokemonDescription;
    }

    public void setPokemonDescription(JTextArea pokemonDescription) {
        JLabel label = new JLabel("DESCRIPTION");
            label.setForeground(FOREGROUND_COLOR);
            label.setFont(getFont());

        pokemonDescription.add(label);
        pokemonDescription.setPreferredSize(DESCRIPTION_CONTAINER_SIZE);
        pokemonDescription.setBackground(BACKGROUND_COLOR);
        pokemonDescription.setForeground(FOREGROUND_COLOR);
        pokemonDescription.setFont(getFont());
        pokemonDescription.isEnabled();
        pokemonDescription.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20),
                pokemonDescription.getBorder()));
        pokemonDescription.setLineWrap(true);
        pokemonDescription.setWrapStyleWord(true);
        this.pokemonDescription = pokemonDescription;
    }

    public JPanel getPokemonDescriptionContainer() {
        return pokemonDescriptionContainer;
    }

    public void setPokemonDescriptionContainer(JPanel pokemonDescriptionContainer) {
        this.pokemonDescriptionContainer = pokemonDescriptionContainer;
    }
}
