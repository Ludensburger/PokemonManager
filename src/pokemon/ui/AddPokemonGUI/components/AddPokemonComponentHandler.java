package pokemon.ui.AddPokemonGUI.components;

import pokemon.Pokedex;
import pokemon.Pokemon;
import pokemon.PokemonType;
import pokemon.ui.UIRunner;
import pokemon.util.FontHandler;
import pokemon.util.ImageHandler;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class AddPokemonComponentHandler {
    private JLabel labelBackground;
    private JPanel titleBarContainer;
    private JPanel bodyContainer;
    private NameContainer pokemonNameContainer;
    private SelectFileContainer selectFileContainer;
    private TypeComboBoxContainer pokemonTypeContainer;
    private DescriptionBoxContainer pokemonDescriptionContainer;
    private AddPokemonButtonsContainer addPokemonButtonsContainer;
    private JPanel fileImageContainer;
    private JLabel fileImageLabel;
    private File fileImage;
    private String pokemonName;
    private String pokemonDescription;
    private PokemonType primaryType;
    private PokemonType secondaryType;
    private JButton cancelButton;
    private JButton saveButton;
    private int pokemonId;
    private Pokedex pokedex;
    private Font font;
    private static final String FONT_NAME = "pokemonRedBlue.ttf";
    private static final float FONT_SIZE = 16f;
    private static final Color BACKGROUND_COLOR = Color.black;
    private static final Color FOREGROUND_COLOR = Color.white;
    private static final int IMAGE_WIDTH = 170;
    private static final int IMAGE_HEIGHT = 170;


    public AddPokemonComponentHandler(JFrame frame, Pokedex pokedex, ImageIcon backgroundImage) throws IOException, FontFormatException {
        setFont(new FontHandler().getFont(FONT_NAME, FONT_SIZE));
        setPokedex(pokedex);
        setPokemonId(pokedex.getPokemonHashMap().size() + 1);
        setFileImageLabel(new JLabel());
        setFileImageContainer(new JPanel());
        setPokemonNameContainer(new NameContainer(getFont(), FOREGROUND_COLOR, BACKGROUND_COLOR));
        setPokemonTypeContainer(new TypeComboBoxContainer(getFont(), FOREGROUND_COLOR, BACKGROUND_COLOR));
        setPokemonDescriptionContainer(new DescriptionBoxContainer(getFont(), FOREGROUND_COLOR, BACKGROUND_COLOR));
        setSelectFileContainer(new SelectFileContainer(getFont(), FOREGROUND_COLOR, BACKGROUND_COLOR, getPokemonId(), getBodyContainer()));
        setBodyContainer(new JPanel());
        setButtonsContainer(new AddPokemonButtonsContainer(getFont(), FOREGROUND_COLOR, BACKGROUND_COLOR));
        setCancelButton(getButtonsContainer().getCancelButton());
        setSaveButton(getButtonsContainer().getSavePokemon());
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
            labelBackground.add(getButtonsContainer().getContainer(), BorderLayout.SOUTH);
        this.labelBackground = labelBackground;
    }

    public JPanel getBodyContainer() {
        return bodyContainer;
    }

    public void setBodyContainer(JPanel bodyContainer) {
        bodyContainer.setLayout(new BorderLayout());
        bodyContainer.setOpaque(false);
        bodyContainer.add(getPokemonNameContainer().getContainer(), BorderLayout.NORTH);
        bodyContainer.add(getSelectFileContainer().getContainer(), BorderLayout.WEST);
        bodyContainer.add(getPokemonTypeContainer().getContainer(),  BorderLayout.EAST);
        bodyContainer.add(getPokemonDescriptionContainer().getContainer(), BorderLayout.SOUTH);
        bodyContainer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20),
                bodyContainer.getBorder()));

        this.bodyContainer = bodyContainer;
    }

    public void selectFile(ActionEvent e) throws RuntimeException {
        if(e.getSource() == getSelectFileContainer().getSelectFileButton()) {
            JFileChooser fileChooser = new JFileChooser();

            int response = fileChooser.showSaveDialog(getBodyContainer());

            if(response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                String newFileName = String.format("%03d", getPokemonId()) + ".png";

                File newFile = new File(new ImageHandler().getPOKEMONS_FOLDER(), newFileName);
                setFileImage(newFile);
                try {
                    // Move the original file to the new location
                    Files.copy(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("File saved and uploaded to: " + newFile.getAbsolutePath());
                    Image rawImage = ImageIO.read(file);
                    Image scaledImage = rawImage.getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH);
                    ImageIcon image = new ImageIcon(scaledImage);
                    getFileImageLabel().setIcon(image);
                } catch (RuntimeException | IOException ex) {
                    throw new RuntimeException();
                }
            }
        }
    }

    public JPanel getFileImageContainer() {
        return fileImageContainer;
    }

    public void setFileImageContainer(JPanel fileImageContainer) {
        fileImageContainer.add(getFileImageLabel());
        fileImageContainer.setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
        fileImageContainer.setOpaque(false);
        this.fileImageContainer = fileImageContainer;
    }

    public SelectFileContainer getSelectFileContainer() {
        return selectFileContainer;
    }

    public void setSelectFileContainer(SelectFileContainer selectFileContainer) {
        selectFileContainer.getSelectFileButton().addActionListener(this::selectFile);
        this.selectFileContainer = selectFileContainer;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == getCancelButton()) {
                    if(getFileImage() != null) {
                        boolean deleted = getFileImage().delete();
                        if(deleted){
                            System.out.println("File: " + getFileImage().getName() + " has been deleted!");
                            getFileImageLabel().setIcon(null);
                        } else {
                            System.out.println("Trouble deleting file!");
                        }
                    }
                    try {
                        UIRunner.getInstance().openPokedexGUI();
                    } catch (LineUnavailableException | IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    UIRunner.getInstance().closeAddPokemonGUI();
                }
            }
        });
        this.cancelButton = cancelButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(JButton saveButton) {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPokemonName(getPokemonNameContainer().getNameTextField().getText().toUpperCase());
                setPokemonDescription(getPokemonDescriptionContainer().getDescriptionTextArea().getText().toUpperCase());
                setPrimaryType((PokemonType) getPokemonTypeContainer().getPrimaryTypeJComboBox().getSelectedItem());
                setSecondaryType((PokemonType) getPokemonTypeContainer().getSecondaryTypeJComboBox().getSelectedItem());

                if(e.getSource() == getSaveButton()) {
                    Pokemon.PokemonBuilder newPokemon = new Pokemon.PokemonBuilder(getPokemonName(), getPokemonId());
                    newPokemon.setDescription(getPokemonDescription());
                    newPokemon.setPrimaryType(getPrimaryType());
                    if(getSecondaryType() != null) {
                        newPokemon.setSecondaryType(getSecondaryType());
                    } else {
                        newPokemon.setSecondaryType(null);
                    }

                    getPokedex().addPokemon(newPokemon.build());
                    try {
                        getPokedex().savePokedex();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    JOptionPane.showMessageDialog(null, "Pokemon saved!");
                    UIRunner.getInstance().closeAddPokemonGUI();

                    try {
                        UIRunner.getInstance().restartPokedexGUI();
                    } catch (LineUnavailableException | IOException | CloneNotSupportedException |
                             UnsupportedAudioFileException | FontFormatException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        this.saveButton = saveButton;
    }

    public NameContainer getPokemonNameContainer() {
        return pokemonNameContainer;
    }

    public void setPokemonNameContainer(NameContainer pokemonNameContainer) {

        this.pokemonNameContainer = pokemonNameContainer;
    }

    public TypeComboBoxContainer getPokemonTypeContainer() {
        return pokemonTypeContainer;
    }

    public void setPokemonTypeContainer(TypeComboBoxContainer pokemonTypeContainer) {
        this.pokemonTypeContainer = pokemonTypeContainer;
    }

    public DescriptionBoxContainer getPokemonDescriptionContainer() {
        return pokemonDescriptionContainer;
    }

    public void setPokemonDescriptionContainer(DescriptionBoxContainer pokemonDescriptionContainer) {
        pokemonDescriptionContainer.getContainer().add(getFileImageContainer(), BorderLayout.NORTH);
        this.pokemonDescriptionContainer = pokemonDescriptionContainer;
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

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
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

    public String getPokemonDescription() {
        return pokemonDescription;
    }

    public void setPokemonDescription(String pokemonDescription) {
        this.pokemonDescription = pokemonDescription;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public JLabel getFileImageLabel() {
        return fileImageLabel;
    }

    public void setFileImageLabel(JLabel fileImageLabel) {
        this.fileImageLabel = fileImageLabel;
    }

    public AddPokemonButtonsContainer getButtonsContainer() {
        return addPokemonButtonsContainer;
    }

    public void setButtonsContainer(AddPokemonButtonsContainer addPokemonButtonsContainer) {
        this.addPokemonButtonsContainer = addPokemonButtonsContainer;
    }

    public File getFileImage() {
        return fileImage;
    }

    public void setFileImage(File fileImage) {
        this.fileImage = fileImage;
    }
}
