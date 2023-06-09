// Written by James P. (21154854)

package gwg6784.swinggpt.gui.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Icon;

import gwg6784.swinggpt.gui.Palette;

/**
 * An extensible button (well, actually a Panel), that supports complex layouts
 * inside it
 */
public class Button extends Panel {
    private static final int ROUNDING = 8;

    private final Set<Runnable> listeners = new HashSet<>();
    private boolean hovered = false;
    private boolean held = false;

    public Button(String string) {
        this(new Label(string));
    }

    public Button(Icon icon) {
        this(new Label(icon));
    }

    public Button() {
        this((Component) null);
    }

    public Button(Component comp) {
        setLayout(new BorderLayout());
        if (comp != null) {
            add(comp, BorderLayout.CENTER);
        }

        setBackground(Palette.TRANSPARENT);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Runnable listener : listeners) {
                    listener.run();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setHeld(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setHeld(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setHovered(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setHovered(false);
            }
        });
    }

    /**
     * Overridden to draw rounded hover / click effects
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color color = Palette.BACKGROUND_1;

        if (this.hovered) {
            if (this.held) {
                color = Palette.ACCENT_DARK_TRANSPARENT;
            } else {
                color = Palette.ACCENT_TRANSPARENT;
            }
        }

        g2d.setColor(color);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), ROUNDING, ROUNDING);

        g2d.dispose();

        super.paint(g);
    }

    public void addClickListener(Runnable listener) {
        this.listeners.add(listener);
    }

    private void setHovered(boolean val) {
        this.hovered = val;
        repaint();
    }

    private void setHeld(boolean val) {
        this.held = val;
        repaint();
    }
}
