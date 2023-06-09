package gwg6784.swinggpt.gui.sidebar;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;

import gwg6784.swinggpt.gui.common.Panel;
import gwg6784.swinggpt.gui.listeners.MouseClickedListener;

public class SidebarPanel extends Panel {
    private Map<Object, SidebarItem> items = new HashMap<>();

    public SidebarPanel() {
        super(BORDER_RIGHT);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void addItem(Object key, String name, Runnable onSelect) {
        this.addItem(key, name, onSelect, null);
    }

    public void addItem(Object key, String name, Runnable onSelect, Runnable onDelete) {
        SidebarItem item = new SidebarItem(name);
        item.addMouseListener((MouseClickedListener) a -> onSelect.run());
        items.put(key, item);
        add(item);
        revalidate();
    }

    public void clearItems() {
        this.items.clear();
        removeAll();
        revalidate();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(160, super.getPreferredSize().height);
    }
}
