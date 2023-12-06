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
    private final JLabel pokemonId;
    private final JLabel pokemonName;
    private final JLabel pokemonImage;


    public PokemonPanel() throws IOException, FontFormatException {
        this.pokemonPanel = new JPanel();
        this.pokemonId = new JLabel();
        this.pokemonName = new JLabel();
        this.pokemonImage = new JLabel();

        pokemonPanel.add(pokemonImage);
        pokemonPanel.add(pokemonId);
        pokemonPanel.add(pokemonName);

        initializePokemonPanel();
    }

    public void initializePokemonPanel() throws FontFormatException, IOException {
        Font PokemonRB = new FontHandler().getFont("PokemonRB.ttf");
        getPokemonPanel().setBackground(Color.black);
        getPokemonPanel().setPreferredSize(new Dimension(560, 80));

        getPokemonId().setFont(PokemonRB);
        getPokemonName().setFont(PokemonRB);

        getPokemonName().setBorder(new EmptyBorder(10, 5, 10, 5));
        getPokemonId().setBorder(new EmptyBorder(10, 5, 10, 5));
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
            Image scaledImg = rawImg.getScaledInstance(128, 128, Image.SCALE_SMOOTH);
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
        Font PokemonRB = new FontHandler().getFont("PokemonRB.ttf");
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
        getPokemonPanel().setPreferredSize(new Dimension(450, 450));

        getPokemonId().setFont(PokemonRB);
        getPokemonName().setFont(PokemonRB);
    }

}
