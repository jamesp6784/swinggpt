// Written by James P. (21154854)

package gwg6784.swinggpt.gui.common;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

import gwg6784.swinggpt.gui.Palette;

/**
 * A modern stylized panel
 */
public class Panel extends JPanel {
    public static final int BORDER_TOP = 1;
    public static final int BORDER_LEFT = 2;
    public static final int BORDER_BOTTOM = 4;
    public static final int BORDER_RIGHT = 8;

    /**
     * Creates a Panel
     *
     * @param borderFlags bitwise flags for sides of the panel to be bordered
     */
    public Panel(int borderFlags) {
        setBackground(Palette.BACKGROUND_1);

        Border border = new MatteBorder(
                borderSize(borderFlags, BORDER_TOP),
                borderSize(borderFlags, BORDER_LEFT),
                borderSize(borderFlags, BORDER_BOTTOM),
                borderSize(borderFlags, BORDER_RIGHT),
                Palette.BORDER);

        setBorder(border);
    }

    /**
     * Creates a Panel
     *
     * @param border whether to enable the border on all sides
     */
    public Panel(boolean border) {
        this(BORDER_TOP | BORDER_LEFT | BORDER_BOTTOM | BORDER_RIGHT);
    }

    public Panel() {
        this(0);
    }

    /**
     * Adds a border inside the current border (can be stacked)
     */
    public void addInnerBorder(Border border) {
        setBorder(new CompoundBorder(getBorder(), border));
    }

    private int borderSize(int border, int check) {
        return (border & check) == 0 ? 0 : 1;
    }
}
