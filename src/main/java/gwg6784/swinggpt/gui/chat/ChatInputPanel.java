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

public class ChatInputPanel extends Panel implements Scrollable {
    public static final int INPUT_MAX_HEIGHT = 160;
    public static final int SCROLL_AMOUNT = 6;

    public ChatInputPanel(Consumer<String> submitConsumer) {
        setLayout(new BorderLayout());

        TextArea textArea = new TextArea();

        textArea.getDocument().addDocumentListener((DocumentUpdateListener) e -> getRootPane().revalidate());
        textArea.addKeyListener((KeyPressedListener) e -> {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (e.isShiftDown()) {
                    textArea.append("\n");
                } else {
                    submitConsumer.accept(textArea.getText());
                    textArea.setText("");
                    e.consume();
                }
            }
        });

        add(textArea, BorderLayout.CENTER);
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
