package gwg6784.swinggpt.gui.sidebar;

import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class SidebarPanel extends JPanel {
    private Map<Object, SidebarItem> items = new HashMap<>();

    public SidebarPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void addItem(Object key, String name, Runnable onSelect) {
        SidebarItem item = new SidebarItem(name);
        item.addActionListener(a -> onSelect.run());
        items.put(key, item);
        add(item);
        revalidate();
    }

    public void clearItems() {
        this.items.clear();
        removeAll();
        revalidate();
    }
}
