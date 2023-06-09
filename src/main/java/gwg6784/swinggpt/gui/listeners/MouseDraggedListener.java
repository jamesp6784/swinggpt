// Written by James P. (21154854)

package gwg6784.swinggpt.gui.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Allows us to use a lambda when adding listeners through lambda coercion
 */
@FunctionalInterface
public interface MouseDraggedListener extends MouseMotionListener {
    public void mouseDragged(MouseEvent e);

    public default void mouseMoved(MouseEvent e) {
    }
}
