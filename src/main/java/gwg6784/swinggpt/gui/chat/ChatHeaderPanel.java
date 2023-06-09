// Written by James P. (21154854)

package gwg6784.swinggpt.gui.chat;

import javax.swing.BoxLayout;

import gwg6784.swinggpt.Util;
import gwg6784.swinggpt.gui.Fonts;
import gwg6784.swinggpt.gui.Palette;
import gwg6784.swinggpt.gui.common.Panel;
import gwg6784.swinggpt.gui.common.Label;

/**
 * A header component for the chat panel
 */
public class ChatHeaderPanel extends Panel {
    private final Label headerLabel;
    private final Label subtextLabel;

    public ChatHeaderPanel(String header, String subtext) {
        super(BORDER_BOTTOM);
        addInnerBorder(Util.emptyBorder(16));

        setBackground(Palette.BACKGROUND_2);

        this.headerLabel = new Label(header, Fonts.HEADER);
        this.subtextLabel = new Label(subtext, Fonts.DEFAULT);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(this.headerLabel);
        add(this.subtextLabel);
    }

    public void setHeader(String header) {
        this.headerLabel.setText(header);
    }

    public void setSubtext(String subtext) {
        this.subtextLabel.setText(subtext);
    }
}
