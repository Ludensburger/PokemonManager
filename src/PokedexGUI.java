import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class PokedexGUI extends JFrame {
    List<Pokemon> PokemonObjects = Pokedex.getPokemonList();
    private JPanel pnlPokedex;
    private JPanel pnlSearch;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JPanel pnlMain;
    private JPanel pnlPokemon1;
    private JPanel pnlPokemon3;
    private JPanel pnlPokemon2;
    private JPanel pnlPokemon4;
    private JPanel pnlPokemon5;
    private JPanel pnlPokemon6;
    private JButton backButton;
    private JButton nextButton;
    private JLabel lblName1;
    private JLabel lblName2;
    private JLabel lblName3;
    private JLabel lblName4;
    private JLabel lblName5;
    private JLabel lblName6;
    private JLabel lblNum1;
    private JLabel lblNum2;
    private JLabel lblNum3;
    private JLabel lblNum4;
    private JLabel lblNum5;
    private JLabel lblNum6;
    private JLabel lblImage1;
    private JLabel lblType1;
    private JLabel lblType2;
    private JLabel lblImage3;
    private JLabel lblImage2;
    private JLabel lblImage4;
    private JLabel lblType4;
    private JLabel lblImage5;
    private JLabel lblType5;
    private JLabel lblImage6;
    private JLabel lblType6;
    private JLabel lblType3;

    private int pageSet;

    public int getPageSet() {
        return pageSet;
    }

    public void setPageSet(int pageSet) {
        if(pageSet < 0  || pageSet > PokemonObjects.size()) {
            return;
        } else {
            this.pageSet = pageSet;
        }
    }

    public static void main(String[] args) throws IOException {
        PokedexGUI app = new PokedexGUI();
        ImageIcon icon = new ImageIcon("src" + File.separator + "ico.png");

        app.setContentPane(app.pnlPokedex);
        app.setSize(800, 600);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setTitle("Pokedex");
        app.setIconImage(icon.getImage());
        app.setVisible(true);
        app.setResizable(false);
    }


    public PokedexGUI() {
        try {
            Pokedex.updatePokedex();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        List<JLabel> LabelNames = new ArrayList<>();
        List<JLabel> LabelNumbers = new ArrayList<>();
        List<JLabel> LabelImages = new ArrayList<>();
        List<JLabel> LabelTypes = new ArrayList<>();
        List<JPanel> PanelPokemons = new ArrayList<>();

        initializeComponents(LabelNames, LabelImages, LabelNumbers, LabelTypes, PanelPokemons);
        initializePokemonToPanels(PokemonObjects, LabelNames, LabelImages, LabelNumbers, LabelTypes);

        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                updateActivePokemonToPanels(PokemonObjects, LabelNames, LabelImages, LabelNumbers, LabelTypes);
                super.keyReleased(e);
            }
        });

        nextButton.addActionListener(e -> {
            setPageSet(getPageSet() + 6);
            updateActivePokemonToPanels(PokemonObjects, LabelNames, LabelImages, LabelNumbers, LabelTypes);
        });

        backButton.addActionListener(e -> {
            setPageSet(getPageSet() - 6);
            updateActivePokemonToPanels(PokemonObjects, LabelNames, LabelImages, LabelNumbers, LabelTypes);
        });
    }

    public void updateActivePokemonToPanels(
            List<Pokemon> PokemonObjects,
            List<JLabel> LabelNames,
            List<JLabel> LabelImages,
            List<JLabel> LabelNumbers,
            List<JLabel> LabelTypes) {

        String activeSearch = txtSearch.getText().toUpperCase();

        List<Pokemon> PokemonActiveSearch =  new ArrayList<>();
        for(Pokemon pokemon : PokemonObjects) {
            if(pokemon.getName().contains(activeSearch)) {
                PokemonActiveSearch.add(pokemon);
            }
        }

        int PokemonActiveSearchIndexCount = getPageSet();
        for(int i = 0; i < 6; i++) {
            displayPokemonToPanels(PokemonActiveSearch, LabelNames, LabelImages, LabelNumbers, LabelTypes, PokemonActiveSearchIndexCount,  i, PokemonActiveSearchIndexCount++ < PokemonActiveSearch.size());
        }
    }

    public void initializePokemonToPanels(
            List<Pokemon> PokemonObjects,
            List<JLabel> LabelNames,
            List<JLabel> LabelImages,
            List<JLabel> LabelNumbers,
            List<JLabel> LabelTypes) {

        for (int i = 0; i < 6; i++) {
            displayPokemonToPanels(PokemonObjects, LabelNames, LabelImages, LabelNumbers, LabelTypes, i,  i, i <  PokemonObjects.size());
        }
    }

    public void displayPokemonToPanels(
            List<Pokemon> PokemonObjects,
            List<JLabel> LabelNames,
            List<JLabel> LabelImages,
            List<JLabel> LabelNumbers,
            List<JLabel> LabelTypes,
            int PokemonIndex,
            int PanelIndex,
            Boolean isPokemon) {

        if(isPokemon) {
            String name = PokemonObjects.get(PokemonIndex).getName();
            String idNumber = "#" + String.format("%03d", PokemonObjects.get(PokemonIndex).getId());
            String type = PokemonObjects.get(PokemonIndex).getType();
            String imagePath = "src" + File.separator + "img" + File.separator + "pokemons" + File.separator + idNumber.substring(1) + ".png";
            ImageIcon icon;

            try {
                BufferedImage img = null;
                Image rawImg = ImageIO.read(new File(imagePath));
                Image scaledImg = rawImg.getScaledInstance(128, 128, Image.SCALE_SMOOTH);
                icon = new ImageIcon(scaledImg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            LabelNames.get(PanelIndex).setText(name);
            LabelNumbers.get(PanelIndex).setText(idNumber);
            LabelTypes.get(PanelIndex).setText(type);
            LabelImages.get(PanelIndex).setText("");
            LabelImages.get(PanelIndex).setIcon(icon);
        } else {
            LabelNames.get(PanelIndex).setText("???");
            LabelNumbers.get(PanelIndex).setText("???");
            LabelTypes.get(PanelIndex).setText("???");
            LabelImages.get(PanelIndex).setIcon(null);
            LabelImages.get(PanelIndex).setText("???");
        }
    }

    public void initializeComponents(
            List<JLabel> LabelNames,
            List<JLabel> LabelImages,
            List<JLabel> LabelNumbers,
            List<JLabel> LabelTypes,
            List<JPanel> PanelPokemons) {

        //////////////////////// Adding to arrays
        Collections.addAll(LabelNames, lblName1, lblName2, lblName3, lblName4, lblName5, lblName6);
        Collections.addAll(LabelNumbers, lblNum1, lblNum2, lblNum3, lblNum4, lblNum5, lblNum6);
        Collections.addAll(LabelImages, lblImage1, lblImage2, lblImage3, lblImage4, lblImage5, lblImage6);
        Collections.addAll(LabelTypes, lblType1, lblType2, lblType3, lblType4, lblType5, lblType6);
        Collections.addAll(PanelPokemons, pnlPokemon1, pnlPokemon2, pnlPokemon3, pnlPokemon4, pnlPokemon5, pnlPokemon6);
        /////////////////////////
    }

}

