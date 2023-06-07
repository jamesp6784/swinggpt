package gwg6784.swinggpt.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.Timer;

public class MessageComponent extends JTextArea {
    private static final int INITIAL_DELAY = 1000;

    private String text;
    private int endIndex = 0;
    private Timer animationTimer;

    public MessageComponent(String text, Color color, boolean animate) {
        this.text = text;

        setEditable(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        setLineWrap(true);
        setWrapStyleWord(true);
        setAlignmentX(LEFT_ALIGNMENT);

        if (animate) {
            this.animationTimer = new Timer(INITIAL_DELAY, a -> this.animate());
            this.animationTimer.start();
        } else {
            setText(text);
        }
    }

    private void animate() {
        this.endIndex++;

        if (this.endIndex > this.text.length()) {
            this.animationTimer.stop();
            return;
        }

        setText(text.substring(0, this.endIndex));

        int delay;

        switch (this.text.charAt(this.endIndex - 1)) {
            case ',':
                delay = 80;
                break;
            case '.':
            case ':':
            case ';':
            case '?':
            case '!':
                delay = 160;
                break;
            case '\n':
                delay = 400;
                break;
            default:
                delay = 20;
        }

        this.animationTimer.setInitialDelay(delay);
        this.animationTimer.restart();
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(super.getMaximumSize().width, this.getPreferredSize().height);
    }
}
