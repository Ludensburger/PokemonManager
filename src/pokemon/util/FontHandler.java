package pokemon.util;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FontHandler {

    public FontHandler() {}

    public Font getFont(String name) throws FontFormatException, IOException {
        GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String FOLDER_PATH = File.separator + "pokemon" + File.separator + "util" + File.separator + "font" + File.separator;
        Font FONT = null;

        try {
            InputStream IS = getClass().getResourceAsStream("/pokemon/util/font/" + name);
            if(IS != null) {
                FONT = Font.createFont(Font.TRUETYPE_FONT, IS).deriveFont(16f);
            }
        } catch (FontFormatException e) {
            throw new FontFormatException("FontHandler Error: Font file is bad.");
        } catch (IOException e) {
            throw new IOException();
        }

        GE.registerFont(FONT);
        return FONT;
    }
}
