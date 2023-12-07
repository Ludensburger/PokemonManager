package pokemon.ui.PokedexGUI;

import pokemon.Pokemon;
import pokemon.util.FontHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class PokemonPanel implements Cloneable {
    private final JPanel pokemonPanel;

    private final JPanel components;

    private final JLabel backgroundImage;
    private final JLabel pokemonId;
    private final JLabel pokemonName;
    private final JLabel pokemonImage;


    public PokemonPanel() throws IOException, FontFormatException {
        this.pokemonPanel = new JPanel();
        this.pokemonId = new JLabel();
        this.pokemonName = new JLabel();
        this.pokemonImage = new JLabel();
        this.components = new JPanel();
        this.backgroundImage = new JLabel();

        initializePokemonPanel();
    }

    public void initializePokemonPanel() throws FontFormatException, IOException {
        Font PokemonRB = new FontHandler().getFont("PokemonRB.ttf");
        getPokemonPanel().setBackground(Color.black);
        getPokemonPanel().setPreferredSize(new Dimension(560, 80));

        getPokemonId().setFont(PokemonRB);
        getPokemonName().setFont(PokemonRB);

        getComponents().setPreferredSize(new Dimension(400, 105));
        getComponents().setLayout(new BorderLayout());
        getPokemonPanel().add(getBackgroundImage());
        getPokemonPanel().add(getComponents());

        getComponents().setBackground(Color.black);
        getComponents().add(getPokemonImage(), BorderLayout.WEST);
        getComponents().add(getPokemonId(), BorderLayout.CENTER);
        getComponents().add(getPokemonName(), BorderLayout.EAST);
    }

    public JPanel getPokemonPanel() {
        return pokemonPanel;
    }

    public JLabel getPokemonId() {
        return pokemonId;
    }

    public JLabel getPokemonName() {
        return pokemonName;
    }

    public JLabel getPokemonImage() {
        return pokemonImage;
    }

    public JPanel getComponents() { return components; }

    public JLabel getBackgroundImage() { return backgroundImage; }

    public void setPokemonId(Integer pokemonId) {
        this.getPokemonId().setText(String.format("%03d", pokemonId));
    }

    public void setPokemonName(String pokemonName) {
        this.getPokemonName().setText(pokemonName);
    }

    public void setPokemonImage(Integer pokemonId) {
        String FolderPath = "src" + File.separator + "img" + File.separator + "pokemons" + File.separator;
        String ImagePath = FolderPath + String.format("%03d", pokemonId) + ".png";
        ImageIcon icon;

        try {
            BufferedImage img = null;
            Image rawImg = ImageIO.read(new File(ImagePath));
            Image scaledImg = rawImg.getScaledInstance(86, 86, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.getPokemonImage().setIcon(icon);
    }


    public void setPokemon(Pokemon pokemon) {
        setPokemonId(pokemon.getId());
        setPokemonName(pokemon.getName());
        setPokemonImage(pokemon.getId());
    }
    @Override
    public PokemonPanel clone() throws CloneNotSupportedException {
        PokemonPanel clone = (PokemonPanel) super.clone();
        try {
            return new PokemonPanel();
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }
}

class EnlargedPokemonPanel extends PokemonPanel {
    public EnlargedPokemonPanel() throws CloneNotSupportedException, IOException, FontFormatException {
        super.clone();
    }

    @Override
    public void setPokemonImage(Integer pokemonId) {
        String FolderPath = "src" + File.separator + "img" + File.separator + "pokemons" + File.separator;
        String ImagePath = FolderPath + String.format("%03d", pokemonId) + ".png";
        ImageIcon icon;

        try {
            BufferedImage img = null;
            Image rawImg = ImageIO.read(new File(ImagePath));
            Image scaledImg = rawImg.getScaledInstance(512, 512, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.getPokemonImage().setIcon(icon);
    }

    @Override
    public void initializePokemonPanel() throws IOException, FontFormatException {
        String FolderPath = "src" + File.separator + "img" + File.separator + "pokedex" + File.separator;
        String ImagePath = FolderPath + "test-bg.jpg";
        ImageIcon icon;

        try {
            BufferedImage img = null;
            Image rawImg = ImageIO.read(new File(ImagePath));
            Image scaledImg = rawImg.getScaledInstance(512, 512, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Font PokemonRB = new FontHandler().getFont("PokemonRB.ttf");
        getPokemonPanel().setBackground(Color.black);
        getPokemonPanel().setPreferredSize(new Dimension(560, 560));

        getPokemonId().setFont(PokemonRB);
        getPokemonName().setFont(PokemonRB);

        getComponents().setPreferredSize(new Dimension(560, 560));
        getComponents().setLayout(new BorderLayout());
        getPokemonPanel().add(getBackgroundImage());
        getPokemonPanel().add(getComponents());

        getComponents().setBackground(Color.black);
        getComponents().add(getPokemonImage(), BorderLayout.CENTER);
        getComponents().add(getPokemonId(), BorderLayout.NORTH);
        getComponents().add(getPokemonName(), BorderLayout.SOUTH);
    }

}
