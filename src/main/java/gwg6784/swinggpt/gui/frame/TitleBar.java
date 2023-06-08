package gwg6784.swinggpt.gui.frame;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleBar extends JPanel {
    private JLabel titleLabel;

    public TitleBar(String title) {
        this.titleLabel = new JLabel(title);

        add(this.titleLabel);
    }

    public void setTitle(String title) {
        this.titleLabel.setText(title);
    }
}
