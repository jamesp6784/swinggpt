package gwg6784.swinggpt.gui.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Allows us to use a lambda when adding listeners through lambda coercion
 */
@FunctionalInterface
public interface MouseClickedListener extends MouseListener {
    public void mouseClicked(MouseEvent e);

    public default void mousePressed(MouseEvent e) {
    }

    public default void mouseReleased(MouseEvent e) {
    }

    public default void mouseEntered(MouseEvent e) {
    }

    public default void mouseExited(MouseEvent e) {
    }
}
