package gwg6784.swinggpt.gui.sidebar;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;

import gwg6784.swinggpt.Util;
import gwg6784.swinggpt.gui.common.Panel;
import gwg6784.swinggpt.gui.common.Separator;

public class SidebarPanel extends Panel {
    private static final int SIDEBAR_WIDTH = 320;

    private Map<Object, SidebarItem> items = new HashMap<>();

    public SidebarPanel() {
        super(BORDER_RIGHT);

        addInnerBorder(Util.emptyBorder(12));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void addItem(Object key, String name, Runnable onSelect) {
        this.addItem(key, name, onSelect, null);
    }

    public void addItem(Object key, String name, Runnable onSelect, Runnable onDelete) {
        SidebarItem item = new SidebarItem(name, onDelete != null);

        item.addClickListener(onSelect);

        if (onDelete != null) {
            item.addDeleteClickListener(onDelete);
        }

        items.put(key, item);
        add(item);
        revalidate();
        repaint();
    }

    public void addDivider() {
        add(new Separator());
        revalidate();
        repaint();
    }

    public void clearItems() {
        this.items.clear();
        removeAll();
        revalidate();
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SIDEBAR_WIDTH, super.getPreferredSize().height);
    }
}
