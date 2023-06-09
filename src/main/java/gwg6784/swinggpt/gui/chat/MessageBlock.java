package gwg6784.swinggpt.gui.chat;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.Timer;

import gwg6784.swinggpt.gui.Palette;
import gwg6784.swinggpt.gui.common.TextArea;

public class MessageBlock extends TextArea {
    private static final int INITIAL_DELAY = 20;

    private String text;
    private int animationIndex = 0;
    private Timer animationTimer;

    public MessageBlock(String text, Color color, boolean animate) {
        this.text = text;

        setBackground(Palette.BACKGROUND_2);

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
        char nextChar = this.text.charAt(this.animationIndex++);

        append("" + nextChar);

        if (this.animationIndex >= this.text.length()) {
            this.animationTimer.stop();
            return;
        }

        int delay;

        switch (nextChar) {
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
