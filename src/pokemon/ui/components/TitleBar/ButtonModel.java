package pokemon.ui.components.TitleBar;

import pokemon.util.FontHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public abstract class ButtonModel {
    public static class ButtonUtil {
        private static final String FONT_NAME = "joystix.otf";
        private static final float FONT_SIZE = 16f;
        private static final Dimension BUTTON_SIZE = new Dimension(50, 50);
        private static final Color TEXT_FOREGROUND_COLOR = Color.WHITE;
        private static final Color BUTTON_BACKGROUND_DEFAULT_COLOR = Color.BLACK;
        private static final Color BUTTON_BACKGROUND_SELECTED_COLOR = Color.RED;

        public static void setupButton(JButton button, String text, ActionListener actionListener) throws IOException, FontFormatException {
            button.setPreferredSize(BUTTON_SIZE);
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setFont(new FontHandler().getFont(FONT_NAME, FONT_SIZE));
            button.setForeground(TEXT_FOREGROUND_COLOR);
            button.setBackground(BUTTON_BACKGROUND_DEFAULT_COLOR);
            button.setText(text);

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    actionListener.actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, null));
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    button.setBackground(BUTTON_BACKGROUND_SELECTED_COLOR);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    button.setBackground(BUTTON_BACKGROUND_DEFAULT_COLOR);
                }
            });
        }
    }
}
