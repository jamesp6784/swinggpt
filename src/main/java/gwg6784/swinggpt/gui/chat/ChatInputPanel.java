package gwg6784.swinggpt.gui.chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Scrollable;

import gwg6784.swinggpt.gui.listeners.DocumentUpdateListener;
import gwg6784.swinggpt.gui.listeners.KeyPressedListener;

public class ChatInputPanel extends JPanel implements Scrollable {
    public static final int INPUT_MAX_HEIGHT = 160;
    public static final int SCROLL_AMOUNT = 6;

    public ChatInputPanel(Consumer<String> submitConsumer) {
        setLayout(new BorderLayout());

        JTextArea jta = new JTextArea();

        jta.getDocument().addDocumentListener((DocumentUpdateListener) e -> getRootPane().revalidate());
        jta.addKeyListener((KeyPressedListener) e -> {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (e.isShiftDown()) {
                    jta.append("\n");
                } else {
                    submitConsumer.accept(jta.getText());
                    jta.setText("");
                    e.consume();
                }
            }
        });

        add(jta, BorderLayout.CENTER);
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
