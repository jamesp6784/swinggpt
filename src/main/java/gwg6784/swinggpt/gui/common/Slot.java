// Written by James P. (21154854)

package gwg6784.swinggpt.gui.common;

import java.awt.BorderLayout;
import java.awt.Component;

/**
 * A utility component to easily switch out the contents
 * for navigation, etc.
 */
public class Slot extends Panel {
    private Component inner;

    public Slot(Component inner) {
        setLayout(new BorderLayout());

        if (inner != null) {
            this.inner = inner;
            add(inner, BorderLayout.CENTER);
        }
    }

    public Slot() {
        this(null);
    }

    public void set(Component inner) {
        removeAll();
        add(inner);
        this.inner = inner;
        revalidate();
    }

    public Component get() {
        return this.inner;
    }
}
