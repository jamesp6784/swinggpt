package gwg6784.swinggpt.gui.common;

import java.awt.Point;

import javax.swing.SwingUtilities;

import gwg6784.swinggpt.gui.frame.Frame;
import gwg6784.swinggpt.gui.listeners.MouseDraggedListener;
import gwg6784.swinggpt.gui.listeners.MousePressedListener;

public class DraggablePanel extends Panel {
    private int startX;
    private int startY;

    public DraggablePanel(int borderFlags) {
        super(borderFlags);
        addMouseListener((MousePressedListener) e -> {
            this.startX = e.getX();
            this.startY = e.getY();
        });

        addMouseMotionListener((MouseDraggedListener) e -> {
            Frame frame = (Frame) SwingUtilities.getWindowAncestor(this);
            Point loc = frame.getLocation();

            frame.setLocation(loc.x + e.getX() - this.startX, loc.y + e.getY() - this.startY);
        });
    }

    public DraggablePanel(boolean border) {
        this(BORDER_TOP | BORDER_LEFT | BORDER_BOTTOM | BORDER_RIGHT);
    }

    public DraggablePanel() {
        this(0);
    }
}
