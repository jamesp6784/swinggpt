package gwg6784.swinggpt.gui.common;

import javax.swing.JTextArea;

import gwg6784.swinggpt.Util;
import gwg6784.swinggpt.gui.Fonts;

public class TextArea extends JTextArea {
    public TextArea() {
        setFont(Fonts.DEFAULT);
        setBorder(Util.emptyBorder(16, 12));
    }
}
