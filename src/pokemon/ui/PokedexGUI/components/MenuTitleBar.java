package pokemon.ui.PokedexGUI.components;

import pokemon.ui.PokedexGUI.actions.DraggableTitleBar;
import pokemon.ui.PokedexGUI.design.MenuGUIDesign;
import pokemon.util.FontHandler;
import pokemon.util.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MenuTitleBar implements MenuGUIDesign {

    private JPanel titlePanel;
    private JLabel icon;
    private JLabel title;
    private JPanel buttonContainer;
    private JButton exitButton;
    private JButton minimizeButton;
    private final static Dimension BUTTON_SIZE = (new Dimension(50, 50));
    private final static Dimension BUTTON_CONTAINER_SIZE = (new Dimension(100, 50));
    private final static Dimension TITLE_PANEL_SIZE = (new Dimension(1280, 50));
    private static final String FONT_NAME = "pokemonRedBlue.ttf";
    private static final float FONT_SIZE = 16f;

    public MenuTitleBar(JFrame menuFrame) throws IOException, FontFormatException {
        setExitButton(new JButton());
        setMinimizeButton(new JButton(), menuFrame);
        setIcon(new JLabel());
        setTitle(new JLabel());
        setButtonContainer(new JPanel());
        setTitlePanel(new JPanel());

        DraggableTitleBar draggable = new DraggableTitleBar(getTitlePanel(), menuFrame);

        menuFrame.addMouseMotionListener(draggable);
        menuFrame.addMouseListener(draggable);
    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(JPanel titlePanel) {
            titlePanel.setLayout(new BorderLayout());
            titlePanel.setBackground(TitleBar_COLOR_DEFAULT());
            titlePanel.setPreferredSize(TITLE_PANEL_SIZE);

            titlePanel.add(getIcon(), BorderLayout.WEST);
            titlePanel.add(getTitle(), BorderLayout.CENTER);
            titlePanel.add(getButtonContainer(), BorderLayout.EAST);

        this.titlePanel = titlePanel;
    }

    public JLabel getIcon() {
        return icon;
    }

    public void setIcon(JLabel icon) {
        ImageIcon ICON = new ImageHandler().getPokedexImage("pokeballSpinning.gif");

            icon.setIcon(ICON);
            icon.setLayout(new BorderLayout());

        this.icon = icon;
    }

    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel title) throws IOException, FontFormatException {
        Font TITLE_FONT = new FontHandler().getFont(FONT_NAME);

            title.setText("Pokedex");
            title.setFont(TITLE_FONT);
            title.setForeground(TitleBarText_COLOR_DEFAULT());

        this.title = title;
    }

    public JPanel getButtonContainer() {
        return buttonContainer;
    }

    public void setButtonContainer(JPanel buttonContainer) {
            buttonContainer.setLayout(new BorderLayout());
            buttonContainer.setPreferredSize(BUTTON_CONTAINER_SIZE);
            buttonContainer.setOpaque(false);

            buttonContainer.add(getExitButton(), BorderLayout.EAST);
            buttonContainer.add(getMinimizeButton(), BorderLayout.CENTER);

        this.buttonContainer = buttonContainer;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public void setExitButton(JButton exitButton) throws IOException, FontFormatException {
        Font EXITBUTTON_FONT = new FontHandler().getFont(FONT_NAME).deriveFont(FONT_SIZE);

            exitButton.setPreferredSize(BUTTON_SIZE);
            exitButton.setFocusPainted(false);
            exitButton.setBorderPainted(false);
            exitButton.setFont(EXITBUTTON_FONT);
            exitButton.setForeground(TitleBarText_COLOR_DEFAULT());
            exitButton.setBackground(TitleBar_COLOR_DEFAULT());
            exitButton.setText("X");

            exitButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    System.exit(0);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    exitButton.setBackground(TitleBarButton_COLOR_SELECTED());
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    exitButton.setBackground(TitleBar_COLOR_DEFAULT());
                }
            });

        this.exitButton = exitButton;
    }

    public JButton getMinimizeButton() {
        return minimizeButton;
    }

    public void setMinimizeButton(JButton minimizeButton, JFrame menuFrame) throws IOException, FontFormatException {
        Font MINIMIZEBUTTON_FONT = new FontHandler().getFont(FONT_NAME).deriveFont(FONT_SIZE);

            minimizeButton.setPreferredSize(BUTTON_SIZE);
            minimizeButton.setFocusPainted(false);
            minimizeButton.setBorderPainted(false);
            minimizeButton.setFont(MINIMIZEBUTTON_FONT);
            minimizeButton.setForeground(TitleBarText_COLOR_DEFAULT());
            minimizeButton.setBackground(TitleBar_COLOR_DEFAULT());
            minimizeButton.setText("-");

            minimizeButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    menuFrame.setExtendedState(Frame.ICONIFIED);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    minimizeButton.setBackground(TitleBarButton_COLOR_SELECTED());
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    minimizeButton.setBackground(TitleBar_COLOR_DEFAULT());
                }
            });

        this.minimizeButton = minimizeButton;
    }
}
