// Written by James P. (21154854)

package gwg6784.swinggpt.gui.chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

import javax.swing.Scrollable;

import gwg6784.swinggpt.gui.common.Panel;
import gwg6784.swinggpt.gui.common.TextArea;
import gwg6784.swinggpt.gui.listeners.DocumentUpdateListener;
import gwg6784.swinggpt.gui.listeners.KeyPressedListener;

/**
 * The scrollable input wrapper for the chat panel
 */
public class ChatInputPanel extends Panel implements Scrollable {
    public static final int INPUT_MAX_HEIGHT = 160;
    public static final int SCROLL_AMOUNT = 6;

    private TextArea textArea;

    public ChatInputPanel(Consumer<String> submitConsumer) {
        setLayout(new BorderLayout());

        this.textArea = new TextArea();

        this.textArea.getDocument().addDocumentListener((DocumentUpdateListener) e -> getRootPane().revalidate());
        this.textArea.addKeyListener((KeyPressedListener) e -> {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // Shift + enter to create newline
                if (e.isShiftDown()) {
                    textArea.append("\n");
                } else {
                    // Otherwise we submit
                    submitConsumer.accept(textArea.getText());
                    e.consume();
                }
            }
        });

        this.textArea.setPlaceholder("Send a message");

        add(textArea, BorderLayout.CENTER);
    }

    public void clear() {
        this.textArea.setText("");
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        // Cap max height before scrolling starts
        Dimension pref = this.getPreferredSize();
        return new Dimension(pref.width, Math.min(pref.height, INPUT_MAX_HEIGHT));
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
