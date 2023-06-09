// Written by James P. (21154854)

package gwg6784.swinggpt.gui.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Allows us to use a lambda when adding listeners through lambda coercion
 */
@FunctionalInterface
public interface KeyPressedListener extends KeyListener {
    @Override
    public default void keyTyped(KeyEvent e) {
    }

    @Override
    public default void keyReleased(KeyEvent e) {
    }
}
