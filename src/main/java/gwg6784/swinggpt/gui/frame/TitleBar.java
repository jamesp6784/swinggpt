// Written by James P. (21154854)

package gwg6784.swinggpt.gui.frame;

import java.awt.BorderLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;

import gwg6784.swinggpt.Util;
import gwg6784.swinggpt.gui.Fonts;
import gwg6784.swinggpt.gui.Icons;
import gwg6784.swinggpt.gui.common.Button;
import gwg6784.swinggpt.gui.common.DraggablePanel;
import gwg6784.swinggpt.gui.common.Label;
import gwg6784.swinggpt.gui.common.Panel;
import gwg6784.swinggpt.services.Services;
import gwg6784.swinggpt.services.WindowService;

public class TitleBar extends DraggablePanel {
    private WindowService windowService = Services.get(WindowService.class);

    private final String title;

    public TitleBar(String title) {
        super(BORDER_BOTTOM);
        this.title = title;

        addInnerBorder(Util.emptyBorder(12, 8));

        setLayout(new BorderLayout());

        ButtonGroup rightGroup = new ButtonGroup();
        rightGroup.addButton(Icons.MINIMIZE_ICON, () -> this.windowService.minimize());
        rightGroup.addButton(Icons.CLOSE_ICON, () -> this.windowService.close());

        add(new Label(Icons.LOGO_ICON), BorderLayout.WEST);
        add(rightGroup, BorderLayout.EAST);
    }

    private static class ButtonGroup extends Panel {
        private boolean addedFirst = false;

        public ButtonGroup() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        }

        public void addButton(Icon icon, Runnable listener) {
            if (addedFirst) {
                add(Box.createHorizontalStrut(16));
            }

            Button button = new Button(icon);
            button.addClickListener(listener);
            add(button);

            addedFirst = true;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.setFont(Fonts.DEFAULT);

        FontMetrics metrics = g2d.getFontMetrics();

        float xOffset = metrics.stringWidth(this.title) / 2.0f;
        float yOffset = metrics.getAscent() / 4.0f;

        float centerX = (float) getBounds().getCenterX();
        float centerY = (float) getBounds().getCenterY();

        g2d.drawString(this.title, centerX - xOffset, centerY - yOffset);

        g2d.dispose();
    }
}
