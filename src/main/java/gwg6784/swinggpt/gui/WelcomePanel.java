package gwg6784.swinggpt.gui;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;

import gwg6784.swinggpt.gui.common.Label;
import gwg6784.swinggpt.gui.common.Panel;

public class WelcomePanel extends Panel {
    public WelcomePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBackground(Palette.BACKGROUND_2);

        Label logoLabel = new Label("SwingGPT", Fonts.LOGO);
        logoLabel.setHorizontalAlignment(Label.CENTER);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Label instructionsLabel = new Label("Click 'New chat' to get started.");
        instructionsLabel.setHorizontalAlignment(Label.CENTER);
        instructionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(logoLabel);
        add(instructionsLabel);
        add(Box.createVerticalGlue());
    }
}
