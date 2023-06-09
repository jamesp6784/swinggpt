package gwg6784.swinggpt.gui.common;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Icon;

import gwg6784.swinggpt.gui.Palette;

public class Button extends Panel {
    private static final int ROUNDING = 16;

    private Set<Runnable> listeners = new HashSet<>();

    public Button(String string) {
        this(new Label(string));
    }

    public Button(Icon icon) {
        this(new Label(icon));
    }

    public Button(Component comp) {
        add(comp);
        setBackground(Palette.TRANSPARENT);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Palette.DEBUG);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), ROUNDING, ROUNDING);

        g2d.dispose();

        super.paint(g);
    }

    public void addClickListener(Runnable listener) {
        this.listeners.add(listener);
    }
}
