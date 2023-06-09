// Written by James P. (21154854)

package gwg6784.swinggpt.gui.chat;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import gwg6784.swinggpt.gui.common.TextArea;

public abstract class MessageBlock extends TextArea {
    public MessageBlock(String text, Color color) {
        setEditable(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        setLineWrap(true);
        setWrapStyleWord(true);
        setBackground(color);
        setText(text);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(400, super.getMaximumSize().height);
    }
}
