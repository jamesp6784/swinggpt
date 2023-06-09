// Written by James P. (21154854)

package gwg6784.swinggpt.gui.common;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextArea;

import gwg6784.swinggpt.Util;
import gwg6784.swinggpt.gui.Fonts;
import gwg6784.swinggpt.gui.Palette;

public class TextArea extends JTextArea {
    private String placeholder;

    public TextArea() {
        setFont(Fonts.DEFAULT);
        setBorder(Util.emptyBorder(16, 12));
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (this.placeholder == null || !getText().isEmpty()) {
            return;
        }

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.setFont(getFont());
        g2d.setColor(Palette.DISABLED);

        g2d.drawString(this.placeholder, 16.0f, (float) getBounds().getCenterY() + 6.0f);

        g2d.dispose();
    }
}
