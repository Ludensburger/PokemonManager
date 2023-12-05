package pokemon.ui;

import javax.swing.*;
import javax.xml.stream.Location;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.util.ArrayList;

import static java.awt.Color.black;
import static java.awt.Color.red;

public class PokedexGUI extends JFrame{

    PokedexGUI() throws CloneNotSupportedException {
        ImageIcon icon = new ImageIcon("src" + File.separator + "ico.png");

        this.setTitle("Pokedex");
        this.setIconImage(icon.getImage());
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setVisible(true);

        ArrayList<PokemonPanel> pokemonPanels = getPokemonPanels();

        Point loc = new Point(600, 100);
        for(PokemonPanel panel : pokemonPanels) {
            panel.getPokemonPanel().setLocation(loc);
            this.add(panel.getPokemonPanel());
            loc.y += 90;
        }

        pokemonPanels.get(pokemonPanels.size()/2).getPokemonPanel().setBackground(Color.red);

        addMouseWheelListener(new MouseWheelListener() {
            int index = pokemonPanels.size() / 2;
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int amount = (e.getWheelRotation());
                if (amount > 0) {
                    if(index < pokemonPanels.size() - 1) {
                        index++;
                    }
                } else {
                    if(index > 0) {
                        index--;
                    }
                }

                for(PokemonPanel panel : pokemonPanels) {
                    if(panel.getPokemonPanel().equals(pokemonPanels.get(index).getPokemonPanel())) {
                        panel.getPokemonPanel().setBackground(Color.red);
                    } else {
                        panel.getPokemonPanel().setBackground(Color.black);
                    }
                }

                System.out.println(amount);
            }
        });

    }

    private static ArrayList<PokemonPanel> getPokemonPanels() throws CloneNotSupportedException {
        ArrayList<PokemonPanel> pokemonPanels = new ArrayList<>();

        PokemonPanel pokemonPanel = new PokemonPanel();

        PokemonPanel pokemonPanel1 = pokemonPanel.clone();
        PokemonPanel pokemonPanel2 = pokemonPanel.clone();
        PokemonPanel pokemonPanel3 = pokemonPanel.clone();
        PokemonPanel pokemonPanel4 = pokemonPanel.clone();
        PokemonPanel pokemonPanel5 = pokemonPanel.clone();

        pokemonPanels.add(pokemonPanel1);
        pokemonPanels.add(pokemonPanel2);
        pokemonPanels.add(pokemonPanel3);
        pokemonPanels.add(pokemonPanel4);
        pokemonPanels.add(pokemonPanel5);
        return pokemonPanels;
    }

    public static class PokemonPanel implements Cloneable {
        private final JPanel pokemonPanel;
        private final JLabel pokemonId;
        private final JLabel pokemonName;
        private final JLabel pokemonImage;
        private final JLabel pokemonType;

        public PokemonPanel() {
            this.pokemonPanel = new JPanel();
            this.pokemonId = new JLabel();
            this.pokemonName = new JLabel();
            this.pokemonImage = new JLabel();
            this.pokemonType = new JLabel();

            pokemonPanel.add(pokemonId);
            pokemonPanel.add(pokemonName);
            pokemonPanel.add(pokemonImage);
            pokemonPanel.add(pokemonType);

            initializePokemonPanel();
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

        public JLabel getPokemonType() {
            return pokemonType;
        }

        public void setPokemonId(String pokemonId) {
            this.pokemonId.setText(pokemonId);
        }

        public void setPokemonName(String pokemonName) {
            this.pokemonName.setText(pokemonName);
        }

        public void setPokemonImage(ImageIcon image) {
            this.pokemonImage.setIcon(image);
        }

        public void setPokemonType(String pokemonType) {
            this.pokemonType.setText(pokemonType);
        }

        public void initializePokemonPanel() {
            getPokemonPanel().setSize(600, 80);
            getPokemonPanel().setBackground(black);
        }

        @Override
        public PokemonPanel clone() throws CloneNotSupportedException {
            PokemonPanel clone = (PokemonPanel) super.clone();
            return new PokemonPanel();
        }
    }
}
