// Written by James P. (21154854)

package gwg6784.swinggpt.gui.sidebar;

import java.awt.BorderLayout;
import java.awt.Dimension;

import gwg6784.swinggpt.Util;
import gwg6784.swinggpt.gui.Icons;
import gwg6784.swinggpt.gui.common.Button;
import gwg6784.swinggpt.gui.common.Label;

public class SidebarItem extends Button {
    private Button deleteButton;

    public SidebarItem(String text, boolean deletable) {
        addInnerBorder(Util.emptyBorder(8, 4));

        add(new Label(text), BorderLayout.CENTER);

        if (deletable) {
            this.deleteButton = new Button(Icons.DELETE_ICON);
            add(this.deleteButton, BorderLayout.EAST);
        }
    }

    public SidebarItem(String text) {
        this(text, false);
    }

    public void addDeleteClickListener(Runnable r) {
        this.deleteButton.addClickListener(r);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(super.getMaximumSize().width, this.getPreferredSize().height);
    }
}
