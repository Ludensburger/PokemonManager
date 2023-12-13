package pokemon.ui.ViewPokemonGUI.components;

import pokemon.Pokemon;
import pokemon.PokemonType;
import pokemon.ui.ViewPokemonGUI.ViewPokemonFrame;
import pokemon.util.AudioHandler;
import pokemon.util.FontHandler;
import pokemon.util.ImageHandler;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ViewPokemonComponentHandler implements Cloneable {

    private Pokemon selectedPokemon;
    private JLabel labelBackground;
    private JPanel titleBarContainer;
    private AudioHandler viewPokemonMusic;
    private JPanel headerContainer;
    private JPanel bodyContainer;
    private JPanel typeImageContainer;
    private JPanel pokemonImageContainer;
    private JPanel infoContainer;
    private JFrame viewPokemonFrame;
    private static final Dimension HEADER_CONTAINER_SIZE = (new Dimension(500, 100));
    private static final Dimension BODY_CONTAINER_SIZE = (new Dimension(500, 570));
    private static final Dimension TYPE_IMAGE_CONTAINER_SIZE  = (new Dimension(500,50));
    private static final Dimension POKEMON_IMAGE_CONTAINER_SIZE = (new Dimension(500, 300));
    private static final Dimension INFO_CONTAINER_SIZE = (new Dimension(500, 270));
    private static final int POKEMON_WIDTH_DEFAULT = 300;
    private static final int POKEMON_HEIGHT_DEFAULT = 300;
    private static final int TYPE_WIDTH_DEFAULT = 100;
    private static final int TYPE_HEIGHT_DEFAULT = 30;
    private static final String FONT_NAME = "pokemonRedBlue.ttf";
    private static final float HEADER_FONT_SIZE = 32f;
    private static final float BODY_FONT_SIZE = 16f;


    public ViewPokemonComponentHandler(JFrame frame, Pokemon pokemon, ImageIcon backgroundImage) throws IOException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        setViewPokemonMusic("rustborocity.wav");
        setSelectedPokemon(pokemon);
        setViewPokemonFrame(frame);
        setTitleBarContainer(new ViewPokemonTitleBar(getViewPokemonFrame(), getViewPokemonMusic()).getTitlePanel());
        setHeaderContainer(new JPanel());
        setTypeImageContainer(new JPanel());
        setPokemonImageContainer(new JPanel());
        setInfoContainer(new JPanel());
        setBodyContainer(new JPanel());
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
        labelBackground.add(getHeaderContainer(), BorderLayout.CENTER);
        labelBackground.add(getBodyContainer(), BorderLayout.SOUTH);

        this.labelBackground = labelBackground;
    }

    public AudioHandler getViewPokemonMusic() {
        return viewPokemonMusic;
    }

    public void setViewPokemonMusic(String fileName) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.viewPokemonMusic = new AudioHandler(fileName);
    }

    public JPanel getHeaderContainer() {
        return headerContainer;
    }

    public void setHeaderContainer(JPanel headerContainer) throws IOException, FontFormatException {
        Font HEADER_FONT = new FontHandler().getFont(FONT_NAME, HEADER_FONT_SIZE);

        headerContainer.setLayout(new BorderLayout());
        headerContainer.setOpaque(true);
        headerContainer.setBackground(Color.black);
        headerContainer.setPreferredSize(HEADER_CONTAINER_SIZE);

        JLabel pokemonName = new JLabel();
        pokemonName.setText(getSelectedPokemon().getName());
        pokemonName.setFont(HEADER_FONT);
        pokemonName.setForeground(Color.white);
        pokemonName.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 20, 0, 0),
                pokemonName.getBorder()));

        JLabel pokemonID = new JLabel();
        pokemonID.setText(String.format("%03d", getSelectedPokemon().getId()));
        pokemonID.setFont(HEADER_FONT);
        pokemonID.setForeground(Color.white);

        pokemonID.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 0, 0, 20),
                pokemonName.getBorder()));

        headerContainer.add(pokemonName, BorderLayout.WEST);
        headerContainer.add(pokemonID, BorderLayout.EAST);

        this.headerContainer = headerContainer;
    }

    public JPanel getBodyContainer() {
        return bodyContainer;
    }

    public void setBodyContainer(JPanel bodyContainer) {
        bodyContainer.setPreferredSize(BODY_CONTAINER_SIZE);
        bodyContainer.setLayout(new FlowLayout());
        bodyContainer.setOpaque(false);

        bodyContainer.add(getPokemonImageContainer());
        bodyContainer.add(getTypeImageContainer());
        bodyContainer.add(getInfoContainer());
        this.bodyContainer = bodyContainer;
    }

    public Pokemon getSelectedPokemon() {
        return selectedPokemon;
    }

    public void setSelectedPokemon(Pokemon pokemon) {
        this.selectedPokemon = pokemon;
    }

    public JPanel getTypeImageContainer() {
        return typeImageContainer;
    }

    public void setTypeImageContainer(JPanel typeImageContainer) {
        PokemonType primaryType = getSelectedPokemon().getPrimaryType();
        String primaryTypeFileName = primaryType + ".png";
        ImageIcon PRIMARY_TYPE_IMAGE = new ImageHandler().getPokemonTypeIcon(
                TYPE_WIDTH_DEFAULT,
                TYPE_HEIGHT_DEFAULT,
                primaryTypeFileName,
                false
        );

        typeImageContainer.setPreferredSize(TYPE_IMAGE_CONTAINER_SIZE);
        typeImageContainer.setOpaque(false);

        JLabel primaryTypeLabel = new JLabel(PRIMARY_TYPE_IMAGE);

        typeImageContainer.add(primaryTypeLabel);

        if(getSelectedPokemon().getSecondaryType() != null) {
            PokemonType secondaryType = getSelectedPokemon().getSecondaryType();
            String secondaryTypeFileName = secondaryType + ".png";
            ImageIcon SECONDARY_TYPE_IMAGE = new ImageHandler().getPokemonTypeIcon(
                    TYPE_WIDTH_DEFAULT,
                    TYPE_HEIGHT_DEFAULT,
                    secondaryTypeFileName,
                    false
            );


            JLabel secondaryTypeLabel = new JLabel(SECONDARY_TYPE_IMAGE);
            typeImageContainer.add(secondaryTypeLabel);
        }

        this.typeImageContainer = typeImageContainer;
    }

    public JPanel getPokemonImageContainer() {
        return pokemonImageContainer;
    }

    public void setPokemonImageContainer(JPanel pokemonImageContainer) throws IOException, FontFormatException {
        ImageIcon IMAGE = new ImageHandler().getPokemonImage(POKEMON_WIDTH_DEFAULT, POKEMON_HEIGHT_DEFAULT, getSelectedPokemon().getId(), false);

        pokemonImageContainer.setLayout(new BorderLayout());
        pokemonImageContainer.setPreferredSize(POKEMON_IMAGE_CONTAINER_SIZE);
        pokemonImageContainer.setOpaque(false);

        JLabel pokemonImage = new JLabel(IMAGE);

        pokemonImageContainer.add(pokemonImage, BorderLayout.CENTER);
        this.pokemonImageContainer = pokemonImageContainer;
    }

    public JPanel getInfoContainer() {
        return infoContainer;
    }

    public void setInfoContainer(JPanel infoContainer) throws IOException, FontFormatException {
        Font BODY_FONT = new FontHandler().getFont(FONT_NAME, BODY_FONT_SIZE);

        infoContainer.setPreferredSize(INFO_CONTAINER_SIZE);

        JTextArea pokemonDescription = new JTextArea(getSelectedPokemon().getDescription().toUpperCase());
        pokemonDescription.setFont(BODY_FONT);
        pokemonDescription.setPreferredSize(INFO_CONTAINER_SIZE);
        pokemonDescription.setLineWrap(true);
        pokemonDescription.setWrapStyleWord(true);

        pokemonDescription.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 20, 10, 20),
                pokemonDescription.getBorder()));



        infoContainer.add(pokemonDescription);
        this.infoContainer = infoContainer;
    }

    public JFrame getViewPokemonFrame() {
        return viewPokemonFrame;
    }

    public void setViewPokemonFrame(JFrame viewPokemonFrame) {
        this.viewPokemonFrame = viewPokemonFrame;
    }

    @Override
    public ViewPokemonComponentHandler clone() {
        try {
            ViewPokemonComponentHandler clone = (ViewPokemonComponentHandler) super.clone();

            clone.setSelectedPokemon(getSelectedPokemon());
            clone.setTypeImageContainer(new JPanel());
            clone.setHeaderContainer(new JPanel());
            clone.setPokemonImageContainer(new JPanel());
            clone.setInfoContainer(new JPanel());
            clone.setBodyContainer(new JPanel());
            clone.setLabelBackground(new JLabel(getLabelBackground().getIcon()));

            return clone;
        } catch (CloneNotSupportedException | IOException | FontFormatException e) {
            throw new AssertionError();
        }
    }
}
