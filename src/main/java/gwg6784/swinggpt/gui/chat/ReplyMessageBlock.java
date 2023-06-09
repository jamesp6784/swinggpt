// Written by James P. (21154854)

package gwg6784.swinggpt.gui.chat;

import javax.swing.Timer;

import gwg6784.swinggpt.gui.Palette;

/**
 * A reply message component
 */
public class ReplyMessageBlock extends MessageBlock {
    private static final int INITIAL_DELAY = 20;

    private final String text;
    private int animationIndex = 0;
    private Timer animationTimer;

    public ReplyMessageBlock(String text, boolean animate, boolean error) {
        super("", Palette.ACCENT);

        if (error) {
            setForeground(Palette.DEBUG);
        }

        this.text = text;

        if (animate) {
            this.animationTimer = new Timer(INITIAL_DELAY, a -> this.animate());
            this.animationTimer.start();
        } else {
            setText(text);
        }
    }

    /**
     * Displays the next character to be shown in the animation
     */
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
}
