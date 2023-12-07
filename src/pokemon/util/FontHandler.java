package pokemon.util;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontHandler {

    public FontHandler() {}

    public Font getFont(String name) throws FontFormatException, IOException {
        GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font font = null;

        try {
            InputStream is = getClass().getResourceAsStream("/pokemon/util/font/pokemonRedBlue.ttf");
            if(is != null) {
                font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(16f);
            }
        } catch (FontFormatException e) {
            throw new FontFormatException("Trouble dealing with font!");
        } catch (IOException e) {
            throw new IOException();
        }

        GE.registerFont(font);
        return font;
    }
}
