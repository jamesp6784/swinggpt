package gwg6784.swinggpt.gui.listeners;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Allows us to use a lambda when adding listeners through lambda coercion
 */
@FunctionalInterface
public interface DocumentUpdateListener extends DocumentListener {
    @Override
    default void insertUpdate(DocumentEvent e) {
        this.update(e);
    }

    @Override
    default void removeUpdate(DocumentEvent e) {
        this.update(e);
    }

    @Override
    default void changedUpdate(DocumentEvent e) {
        this.update(e);
    }

    void update(DocumentEvent e);
}
