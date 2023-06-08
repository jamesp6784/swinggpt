package gwg6784.swinggpt.gui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;

public class Slot extends JPanel {
    public Slot(Component inner) {
        setLayout(new BorderLayout());
        add(inner, BorderLayout.CENTER);
    }

    public void set(Component inner) {
        removeAll();
        add(inner);
        revalidate();
    }
}
