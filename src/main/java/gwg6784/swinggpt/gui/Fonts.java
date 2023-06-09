// Written by James P. (21154854)

package gwg6784.swinggpt.gui;

import java.awt.Font;
import java.io.InputStream;

public final class Fonts {
    private Fonts() {
    }

    private static final Font LATO_REGULAR = loadFont(Font.TRUETYPE_FONT, "fonts/Lato-Regular.ttf");
    private static final Font PROMPT_SEMIBOLD = loadFont(Font.TRUETYPE_FONT, "fonts/Prompt-SemiBold.ttf");

    public static final Font DEFAULT = LATO_REGULAR.deriveFont(Font.PLAIN, 16);
    public static final Font LOGO = PROMPT_SEMIBOLD.deriveFont(Font.BOLD, 32);
    public static final Font HEADER = PROMPT_SEMIBOLD.deriveFont(Font.PLAIN, 24);

    private static Font loadFont(int type, String file) {
        try {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
            Font font = Font.createFont(type, is);
            is.close();
            return font;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
