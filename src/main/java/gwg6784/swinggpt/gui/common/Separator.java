package gwg6784.swinggpt.gui.common;

import java.awt.Dimension;

import javax.swing.border.CompoundBorder;

import gwg6784.swinggpt.Util;
import gwg6784.swinggpt.gui.Palette;

public class Separator extends Panel {
    private static final int PADDING = 8;

    public Separator() {
        super(BORDER_BOTTOM);

        setBackground(Palette.TRANSPARENT);
        addInnerBorder(Util.emptyBorder(PADDING));
        setBorder(new CompoundBorder(Util.emptyBorder(PADDING), getBorder()));
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(super.getMaximumSize().width, 17);
    }
}
