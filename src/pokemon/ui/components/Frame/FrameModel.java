package pokemon.ui.components.Frame;

import pokemon.util.AudioHandler;
import pokemon.util.ImageHandler;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;


public abstract class FrameModel extends JFrame implements Cloneable{
    private JFrame frame;
    private AudioHandler frameMusic;
    private int frameWidth;
    private int frameHeight;
    private int frameLocationX = 0;
    private int frameLocationY = 0;
    private double frameCornerArcWidth = 40.0;
    private double frameCornerArcHeight = 40.0;
    private int iconWidth = 64;
    private int iconHeight = 64;
    private int backgroudWidth = 1440;
    private int backgroundHeight = 720;
    private Image frameIcon = new ImageHandler().getScaledPokedexIcon(getIconWidth(), getIconHeight(), "icon","ico.png", false).getImage();
    private ImageIcon frameBackgroundImage = new ImageHandler().getPokedexImage(getBackgroudWidth(), getBackgroundHeight(), "bg.gif", true);


    public FrameModel(int frameWidth, int frameHeight) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        setFrameWidth(frameWidth);
        setFrameHeight(frameHeight);
        setFrame(this);
        setFrameMusic("rustborocity.wav");
        setFrameBackgroundImage(getFrameBackgroundImage());

        /*
         *  USING CUSTOM TITLE BAR; proceed to components.TitleBar to change title, icon, and buttons at the top of window.
         */
        //  Set TITLE of JFrame window; components.TitleBar for custom title bar
        getFrame().setTitle("Pokedex");

        //  Set ICON_IMAGE of JFrame window; components.TitleBar for custom title bar
        getFrame().setIconImage(getFrameIcon());

        //  Disposes frame when closing
        getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //  Set SIZE of JFrame window
        getFrame().setSize(getFrameWidth(), getFrameHeight());

        //  Set JFrame decorated to false; NECESSARY FOR setShape(), DO NOT MODIFY.
        getFrame().setUndecorated(true);

        //  Set SHAPE of JFrame window
        getFrame().setShape(new RoundRectangle2D.Double(
                getFrameLocationX(),    //  X coordinate location of this shape
                getFrameLocationY(),    //  Y coordinate location of this shape
                getFrameWidth(),         //  width of this shape
                getFrameHeight(),        //  height of this shape
                getFrameCornerArcWidth(),     //  width that rounds off corners
                getFrameCornerArcHeight()     //  height that rounds off corners
        ));

        //  Set LAYOUT of JFrame window
        getFrame().setLayout(new BorderLayout());

        //  Set if JFrame window can be manually resized by the user
        getFrame().setResizable(false);

        //  Set JFrame window to center of user's screen on start
        getFrame().setLocationRelativeTo(null);

        //  Setting JFrame to focusable activates key listener
        getFrame().setFocusable(true);

        getFrame().setVisible(false);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public AudioHandler getFrameMusic() {
        return frameMusic;
    }

    public void setFrameMusic(String fileName) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.frameMusic = new AudioHandler(fileName);
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    public Image getFrameIcon() {
        return frameIcon;
    }

    public void setFrameIcon(Image frameIcon) {
        this.frameIcon = frameIcon;
    }

    public ImageIcon getFrameBackgroundImage() {
        return frameBackgroundImage;
    }

    public void setFrameBackgroundImage(ImageIcon frameBackgroundImage) {
        this.frameBackgroundImage = frameBackgroundImage;
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

    public int getFrameLocationX() {
        return frameLocationX;
    }

    public void setFrameLocationX(int frameLocationX) {
        this.frameLocationX = frameLocationX;
    }

    public int getFrameLocationY() {
        return frameLocationY;
    }

    public void setFrameLocationY(int frameLocationY) {
        this.frameLocationY = frameLocationY;
    }

    public double getFrameCornerArcWidth() {
        return frameCornerArcWidth;
    }

    public void setFrameCornerArcWidth(double frameCornerArcWidth) {
        this.frameCornerArcWidth = frameCornerArcWidth;
    }

    public double getFrameCornerArcHeight() {
        return frameCornerArcHeight;
    }

    public void setFrameCornerArcHeight(double frameCornerArcHeight) {
        this.frameCornerArcHeight = frameCornerArcHeight;
    }
    public int getBackgroudWidth() {
        return backgroudWidth;
    }

    public void setBackgroudWidth(int backgroudWidth) {
        this.backgroudWidth = backgroudWidth;
    }

    public int getBackgroundHeight() {
        return backgroundHeight;
    }
    public void setBackgroundHeight(int backgroundHeight) {
        this.backgroundHeight = backgroundHeight;
    }


    @Override
    public FrameModel clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (FrameModel) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
