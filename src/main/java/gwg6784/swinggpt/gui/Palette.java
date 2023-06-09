package gwg6784.swinggpt.gui;

import java.awt.Color;

public final class Palette {
    private Palette() {
    }

    public static final Color TRANSPARENT = new Color(0, 0, 0, 0);
    public static final Color BACKGROUND_1 = new Color(231, 231, 231);
    public static final Color BACKGROUND_2 = new Color(224, 224, 224);
    public static final Color BORDER = new Color(206, 206, 206);

    public static final Color ACCENT_TRANSPARENT = new Color(116, 170, 156, 100);
    public static final Color ACCENT_DARK_TRANSPARENT = ACCENT_TRANSPARENT.darker();

    public static final Color DEBUG = Color.RED;
    public static final Color DEBUG_2 = Color.BLUE;
}
