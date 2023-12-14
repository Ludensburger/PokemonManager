package pokemon.ui.components.TitleBar;

import pokemon.util.FontHandler;
import pokemon.util.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public abstract class TitleBarModel implements Cloneable{
    private JPanel titlePanel;
    private JLabel icon;
    private JLabel title;
    private JPanel buttonContainer;
    private JButton exitButton;
    private JButton minimizeButton;
    private int iconWidth = 64;
    private int iconHeight = 64;
    private static final Dimension BUTTON_CONTAINER_SIZE = (new Dimension(100, 50));
    private static final Dimension TITLE_PANEL_SIZE = (new Dimension(1280, 50));
    private static final Color PANEL_BACKGROUND_COLOR = Color.black;
    private static final Color TEXT_FOREGROUND_COLOR = Color.white;
    private static final String FONT_NAME = "pokemonRedBlue.ttf";
    private static final float FONT_SIZE = 16f;


    public TitleBarModel(JFrame frame) throws IOException, FontFormatException {
        setExitButton(new JButton());
        setMinimizeButton(new JButton(), frame);
        setIcon(new JLabel());
        setTitle(new JLabel());
        setButtonContainer(new JPanel());
        setTitlePanel(new JPanel());

        SetDraggableFunction draggableFunction = new SetDraggableFunction(getTitlePanel(), frame);

        frame.addMouseMotionListener(draggableFunction);
        frame.addMouseListener(draggableFunction);
    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(JPanel titlePanel) {
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBackground(PANEL_BACKGROUND_COLOR);
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
        ImageIcon ICON = new ImageHandler().getPokedexImage(getIconWidth(), getIconHeight(), "pokeballSpinning.gif", true);

        icon.setIcon(ICON);
        icon.setLayout(new BorderLayout());

        this.icon = icon;
    }

    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel title) throws IOException, FontFormatException {
        Font TITLE_FONT = new FontHandler().getFont(FONT_NAME, FONT_SIZE);

        title.setText("Pokedex");
        title.setFont(TITLE_FONT);
        title.setForeground(TEXT_FOREGROUND_COLOR);

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
        ButtonModel.ButtonUtil.setupButton(exitButton,
                "X",
                e -> System.exit(0));
        this.exitButton = exitButton;
    }

    public JButton getMinimizeButton() {
        return minimizeButton;
    }

    public void setMinimizeButton(JButton minimizeButton, JFrame menuFrame) throws IOException, FontFormatException {
        ButtonModel.ButtonUtil.setupButton(minimizeButton,
                "-",
                e -> menuFrame.setExtendedState(Frame.ICONIFIED));
        this.minimizeButton = minimizeButton;
    }

    public int getIconWidth() {
        return iconWidth;
    }

    public void setIconWidth(int iconWidth) {
        this.iconWidth = iconWidth;
    }

    public int getIconHeight() {
        return iconHeight;
    }

    public void setIconHeight(int iconHeight) {
        this.iconHeight = iconHeight;
    }

    @Override
    public TitleBarModel clone() {
        try {
            return (TitleBarModel) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
