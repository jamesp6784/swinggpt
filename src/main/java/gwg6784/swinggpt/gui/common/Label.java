// Written by James P. (21154854)

package gwg6784.swinggpt.gui.common;

import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;

import gwg6784.swinggpt.gui.Fonts;

public class Label extends JLabel {
    public Label(String text) {
        this(text, Fonts.DEFAULT);
    }

    public Label(String text, Font font, int fontSize) {
        this(text, font.deriveFont(fontSize));
    }

    public Label(String text, Font font) {
        super(text);
        setFont(font);
    }

    public Label(Icon icon) {
        super(icon);
    }
}
