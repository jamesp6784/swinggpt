// Written by James P. (21154854)

package gwg6784.swinggpt.gui.chat;

import java.awt.Component;
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

/**
 * The scrollable chat window
 */
public class MessagePanel extends Panel implements Scrollable {
    public static final int SCROLL_AMOUNT = 14;

    private Label loader;

    public MessagePanel() {
        setBackground(Palette.BACKGROUND_2);

        setBorder(Util.emptyBorder(16));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * Add new prompt to message panel
     */
    public void addPrompt(String prompt) {
        this.addMessageBlock(new PromptMessageBlock(prompt));
    }

    /**
     * Add new reply to message panel
     */
    public void addReply(String message, boolean animate) {
        this.addMessageBlock(new ReplyMessageBlock(message, animate, false));
    }

    /**
     * Add error message to message panel
     */
    public void addError(String message) {
        this.addMessageBlock(new ReplyMessageBlock(message, false, true));
    }

    /**
     * Play loader
     */
    public void setLoading() {
        this.loader = new Label(Icons.LOADER_ICON);
        loader.setAlignmentX(CENTER_ALIGNMENT);
        add(this.loader);
    }

    /**
     * @return whether loader is playing
     */
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

    @Override
    public Component add(Component c) {
        super.add(c);

        this.revalidate();
        this.repaint();

        return c;
    }

    @Override
    public void remove(Component c) {
        super.remove(c);

        this.revalidate();
        this.repaint();
    }
}
