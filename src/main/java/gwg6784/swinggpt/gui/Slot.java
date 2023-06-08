package gwg6784.swinggpt.gui;

import java.awt.Component;

import javax.swing.JPanel;

public class Slot extends JPanel {
    public Slot(Component inner) {
        add(inner);
    }

    public void set(Component inner) {
        removeAll();
        add(inner);
        revalidate();
    }
}
