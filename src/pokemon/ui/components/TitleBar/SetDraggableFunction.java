package pokemon.ui.components.TitleBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SetDraggableFunction implements MouseListener, MouseMotionListener{
        private Point START_DRAG;

        private Point START_LOCATION;

        private JPanel TARGET;

        private JFrame FRAME;

    public SetDraggableFunction(JPanel targetPanel, JFrame targetFrame) {
            setSTART_DRAG(new Point());
            setSTART_LOCATION(new Point());
            setTARGET(targetPanel);
            setFRAME(targetFrame);
    }

    private Point getScreenLocation(MouseEvent e) {
        Point CURSOR_LOCATION = e.getPoint();
        Point TARGET_LOCATION = getTARGET().getLocationOnScreen();

        return new Point(
                (int) (TARGET_LOCATION.getX() + CURSOR_LOCATION.getX()),
                (int) (TARGET_LOCATION.getY() + CURSOR_LOCATION.getY()));
    }

    private Point getOffsetLocation(Point current) {
        int OFFSET_X = (int) (current.getX() - getSTART_DRAG().getX());
        int OFFSET_Y = (int) (current.getY() - getSTART_DRAG().getY());

        return new Point(OFFSET_X, OFFSET_Y);
    }

    private Point getNewLocation(Point offset) {
        int NEW_X = (int) (getSTART_LOCATION().getX() + offset.getX());
        int NEW_Y = (int) (getSTART_LOCATION().getY() + offset.getY());

        return new Point(NEW_X, NEW_Y);
    }

    public Point getSTART_DRAG() {
        return START_DRAG;
    }

    public void setSTART_DRAG(Point START_DRAG) {
        this.START_DRAG = START_DRAG;
    }

    public Point getSTART_LOCATION() {
        return START_LOCATION;
    }

    public void setSTART_LOCATION(Point START_LOCATION) {
        this.START_LOCATION = START_LOCATION;
    }

    public JPanel getTARGET() {
        return TARGET;
    }

    public void setTARGET(JPanel TARGET) {
        this.TARGET = TARGET;
    }

    public JFrame getFRAME() {
        return FRAME;
    }

    public void setFRAME(JFrame FRAME) {
        this.FRAME = FRAME;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
        public void mousePressed(MouseEvent e) {
            setSTART_DRAG(this.getScreenLocation(e));
            setSTART_LOCATION(getFRAME().getLocation());
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

            getFRAME().setLocation(NEW_LOCATION);
        }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
