package gwg6784.swinggpt.gui.common;

import java.awt.BorderLayout;
import java.awt.Component;

public class Slot extends Panel {
    public Slot(Component inner) {
        setLayout(new BorderLayout());

        if (inner != null) {
            add(inner, BorderLayout.CENTER);
        }
    }

    public Slot() {
        this(null);
    }

    public void set(Component inner) {
        removeAll();
        add(inner);
        revalidate();
    }
}
