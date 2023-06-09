package gwg6784.swinggpt.gui.common;

import java.awt.Component;
import java.awt.Point;

import javax.swing.SwingUtilities;

import gwg6784.swinggpt.gui.frame.Frame;
import gwg6784.swinggpt.gui.listeners.MouseDraggedListener;
import gwg6784.swinggpt.gui.listeners.MousePressedListener;

public class DraggableSlot extends Slot {
    private int startX;
    private int startY;

    public DraggableSlot(Component inner) {
        super(inner);

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

    public DraggableSlot() {
        this(null);
    }
}
