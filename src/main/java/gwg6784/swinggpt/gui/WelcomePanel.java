package gwg6784.swinggpt.gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel {
    public WelcomePanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Welcome"), BorderLayout.CENTER);
    }
}
