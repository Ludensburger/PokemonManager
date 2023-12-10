package pokemon.ui.addPokemonGUI.components;

import pokemon.ui.PokedexGUI.design.MenuGUIDesign;
import pokemon.util.FontHandler;
import pokemon.util.ImageHandler;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

public class TitleBar implements MenuGUIDesign {

    private JPanel titlePanel;
    private JLabel icon;
    private JLabel title;
    private JPanel buttonPanel;
    private JButton exit;
    private JButton minimize;

    private JButton menu;


    public TitleBar(JFrame frame, JFrame menu, Clip mainMenuMusic, Clip addPokemonMusic) throws IOException, FontFormatException {
        this.titlePanel = new JPanel();
        this.icon = new JLabel();
        this.title = new JLabel();
        this.buttonPanel = new JPanel();
        this.exit = new JButton();
        this.minimize = new JButton();
        this.menu = new JButton();


        decorateTitleBar();


        class MoveMouseListener implements MouseListener, MouseMotionListener {
            Point START_DRAG = new Point();

            Point START_LOCATION = new Point();

            final JPanel TARGET = getTitlePanel();


            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                START_DRAG = this.getScreenLocation(e);
                START_LOCATION = frame.getLocation();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point CURRENT_LOCATION = getScreenLocation(e);
                Point OFFSET_LOCATION = getOffsetLocation(CURRENT_LOCATION);
                Point NEW_LOCATION = getNewLocation(OFFSET_LOCATION);

                frame.setLocation(NEW_LOCATION);
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }

            Point getScreenLocation(MouseEvent e) {
                Point CURSOR_LOCATION = e.getPoint();
                Point TARGET_LOCATION = this.TARGET.getLocationOnScreen();

                return new Point(
                        (int) (TARGET_LOCATION.getX() + CURSOR_LOCATION.getX()),
                        (int) (TARGET_LOCATION.getY() + CURSOR_LOCATION.getY()));
            }

            Point getOffsetLocation(Point current) {
                int OFFSET_X = (int) (current.getX() - START_DRAG.getX());
                int OFFSET_Y = (int) (current.getY() - START_DRAG.getY());

                return new Point(OFFSET_X, OFFSET_Y);
            }

            Point getNewLocation(Point offset) {
                int NEW_X = (int) (this.START_LOCATION.getX() + offset.getX());
                int NEW_Y = (int) ((this.START_LOCATION.getY()) + offset.getY());

                return new Point(NEW_X, NEW_Y);
            }
        }

        MoveMouseListener MML = new MoveMouseListener();
        frame.addMouseMotionListener(MML);
        frame.addMouseListener(MML);

        getExit().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e){
                super.mouseEntered(e);
                getExit().setBackground(TitleBarButton_COLOR_SELECTED());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                getExit().setBackground(TitleBar_COLOR_DEFAULT());
            }
        });

        getMinimize().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                frame.setExtendedState(Frame.ICONIFIED);
            }

            @Override
            public void mouseEntered(MouseEvent e){
                super.mouseEntered(e);
                getMinimize().setBackground(TitleBarButton_COLOR_SELECTED());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                getMinimize().setBackground(TitleBar_COLOR_DEFAULT());
            }
        });

        getMenu().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                frame.setVisible(false);
                menu.setVisible(true);
                mainMenuMusic.start();
                addPokemonMusic.stop();
            }

            @Override
            public void mouseEntered(MouseEvent e){
                super.mouseEntered(e);
                getMenu().setBackground(TitleBarButton_COLOR_SELECTED());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                getMenu().setBackground(TitleBar_COLOR_DEFAULT());
            }
        });
    }

    public void decorateTitleBar() throws IOException, FontFormatException {
        ImageIcon ICON = new ImageHandler().getPokedexImage("pikachu8bitres.gif");
        Font FONT = new FontHandler().getFont("pokemonRedBlue.ttf");
        getTitlePanel().setLayout(new BorderLayout());
        getTitlePanel().setBackground(TitleBar_COLOR_DEFAULT());

        //  Title Panel
        getTitlePanel().setPreferredSize(new Dimension(500, 50));


        //  Icon
        getIcon().setIcon(ICON);
        getIcon().setLayout(new BorderLayout());
        getTitlePanel().add(getIcon(), BorderLayout.WEST);

        //  Title
        getTitle().setText("Build a Pokemon");
        getTitle().setFont(FONT);
        getTitle().setForeground(TitleBarText_COLOR_DEFAULT());
        getTitlePanel().add(getTitle(), BorderLayout.CENTER);

        //  Button Panel
        getButtonPanel().setLayout(new BorderLayout());
        getButtonPanel().setPreferredSize(new Dimension(200, 50));
        getButtonPanel().setOpaque(false);
        getTitlePanel().add(getButtonPanel(), BorderLayout.EAST);

        //  Exit
        getExit().setPreferredSize(new Dimension(50, 50));
        getExit().setFocusPainted(false);
        getExit().setBorderPainted(false);
        getExit().setText("X");
        getExit().setFont(FONT);
        getExit().setForeground(TitleBarText_COLOR_DEFAULT());
        getExit().setBackground(TitleBar_COLOR_DEFAULT());
        getButtonPanel().add(getExit(), BorderLayout.EAST);

        //  Minimize
        getMinimize().setPreferredSize(new Dimension(50, 50));
        getMinimize().setFocusPainted(false);
        getMinimize().setBorderPainted(false);
        getMinimize().setText("-");
        getMinimize().setFont(FONT);
        getMinimize().setForeground(TitleBarText_COLOR_DEFAULT());
        getMinimize().setBackground(TitleBar_COLOR_DEFAULT());
        getButtonPanel().add(getMinimize(), BorderLayout.CENTER);

        //  Menu
        getMenu().setPreferredSize(new Dimension(100, 50));
        getMenu().setFocusPainted(false);
        getMenu().setBorderPainted(false);
        getMenu().setText("MENU");
        getMenu().setFont(FONT);
        getMenu().setForeground(TitleBarText_COLOR_DEFAULT());
        getMenu().setBackground(TitleBar_COLOR_DEFAULT());
        getButtonPanel().add(getMenu(), BorderLayout.WEST);
    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(JPanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    public JLabel getIcon() {
        return icon;
    }

    public void setIcon(JLabel icon) {
        this.icon = icon;
    }

    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }

    public JButton getExit() {
        return exit;
    }

    public void setExit(JButton exit) {
        this.exit = exit;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JButton getMinimize() {
        return minimize;
    }

    public void setMinimize(JButton minimize) {
        this.minimize = minimize;
    }

    public JButton getMenu() {
        return menu;
    }

    public void setMenu(JButton menu) {
        this.menu = menu;
    }


}
