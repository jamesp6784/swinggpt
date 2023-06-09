package gwg6784.swinggpt.gui.sidebar;

import java.awt.BorderLayout;

import javax.swing.Box;

import gwg6784.swinggpt.gui.Icons;
import gwg6784.swinggpt.gui.common.Button;
import gwg6784.swinggpt.gui.common.Label;

public class SidebarItem extends Button {
    public SidebarItem(String text) {
        add(new Label(text), BorderLayout.WEST);
        add(Box.createHorizontalGlue());
        add(new Button(Icons.DELETE_ICON), BorderLayout.EAST);
    }
}
