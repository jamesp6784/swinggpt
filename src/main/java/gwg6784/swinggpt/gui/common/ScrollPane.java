package gwg6784.swinggpt.gui.common;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

import gwg6784.swinggpt.gui.Palette;

public class ScrollPane extends JScrollPane {
    public ScrollPane(Component view) {
        super(view);
        setBorder(null);
        getViewport().setBackground(Palette.TRANSPARENT);

        setHorizontalScrollBar(null);
        getVerticalScrollBar().setUI(new UI());
    }

    private static class UI extends BasicScrollBarUI {
        @Override
        protected JButton createDecreaseButton(int orientation) {
            return new StubButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return new StubButton();
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            g.setColor(Palette.BACKGROUND_1);
            g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            g.setColor(Palette.BORDER);
            g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
        }
    }

    private static class StubButton extends JButton {
        private StubButton() {
            setFocusable(false);
            setPreferredSize(new Dimension());
        }
    }
}
