package gwg6784.swinggpt.gui.sidebar;

import java.awt.BorderLayout;

import javax.swing.Box;

import gwg6784.swinggpt.gui.Icons;
import gwg6784.swinggpt.gui.common.Button;
import gwg6784.swinggpt.gui.common.Label;
import gwg6784.swinggpt.gui.common.Panel;

public class SidebarItem extends Panel {
    public SidebarItem(String text) {
        setLayout(new BorderLayout());
        // setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(new Label(text), BorderLayout.WEST);
        add(Box.createHorizontalGlue());
        add(new Button(Icons.DELETE_ICON), BorderLayout.EAST);
    }
}
