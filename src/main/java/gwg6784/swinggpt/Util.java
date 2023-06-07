package gwg6784.swinggpt;

import java.awt.Font;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public final class Util {
    private Util() {
    }

    public static Border emptyBorder(int size) {
        return BorderFactory.createEmptyBorder(size, size, size, size);
    }

    public static Border emptyBorder(int hor, int ver) {
        return BorderFactory.createEmptyBorder(ver, hor, ver, hor);
    }

    // private static final Font BASE_FONT = loadFont(Font.TRUETYPE_FONT,
    // "Lato-Regular.ttf");

    // public static final Font FONT_REGULAR = BASE_FONT.deriveFont(Font.PLAIN, 14);

    // private static Font loadFont(int type, String file) {
    // try {
    // InputStream is =
    // Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
    // Font font = Font.createFont(type, is);
    // is.close();
    // return font;
    // } catch (Exception e) {
    // throw new RuntimeException(e);
    // }
    // }
}
