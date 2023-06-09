package gwg6784.swinggpt.gui.chat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.Scrollable;

import gwg6784.swinggpt.gui.Palette;
import gwg6784.swinggpt.gui.common.Panel;

public class MessagePanel extends Panel implements Scrollable {
    public static final int SCROLL_AMOUNT = 14;

    public MessagePanel() {
        setBackground(Palette.BACKGROUND_2);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void addMessage(String message, boolean animate) {
        add(new MessageBlock(message, Color.black, animate));
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return this.getPreferredSize();
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return SCROLL_AMOUNT;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return SCROLL_AMOUNT * 2;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
}
