// Written by James P. (21154854)

package gwg6784.swinggpt.gui.chat;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Scrollable;

import gwg6784.swinggpt.Util;
import gwg6784.swinggpt.gui.Icons;
import gwg6784.swinggpt.gui.Palette;
import gwg6784.swinggpt.gui.common.Label;
import gwg6784.swinggpt.gui.common.Panel;

public class MessagePanel extends Panel implements Scrollable {
    public static final int SCROLL_AMOUNT = 14;

    private Label loader;

    public MessagePanel() {
        setBackground(Palette.BACKGROUND_2);

        setBorder(Util.emptyBorder(16));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void addPrompt(String prompt) {
        this.addMessageBlock(new PromptMessageBlock(prompt));
    }

    public void addReply(String message, boolean animate) {
        this.addMessageBlock(new ReplyMessageBlock(message, animate, false));
    }

    public void addError(String message) {
        this.addMessageBlock(new ReplyMessageBlock(message, false, true));
    }

    public void setLoading() {
        this.loader = new Label(Icons.LOADER_ICON);
        loader.setAlignmentX(CENTER_ALIGNMENT);
        add(this.loader);
    }

    public boolean getLoading() {
        return this.loader != null;
    }

    private void stopLoading() {
        if (this.loader != null) {
            remove(loader);
            this.loader = null;
        }
    }

    private void addMessageBlock(MessageBlock block) {
        this.stopLoading();
        add(block);
        add(Box.createVerticalStrut(16));
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
