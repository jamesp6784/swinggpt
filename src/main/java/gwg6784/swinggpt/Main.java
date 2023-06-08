package gwg6784.swinggpt;

import javax.swing.JFrame;

import gwg6784.swinggpt.gui.MainPanel;
import gwg6784.swinggpt.gui.frame.Frame;
import gwg6784.swinggpt.services.Services;
import gwg6784.swinggpt.services.TitleService;
import gwg6784.swinggpt.services.conversation.ConversationService;

public class Main {
    public static void main(String[] args) {
        Services.add(new ConversationService());
        Services.add(new TitleService("SwingGPT"));

        Frame frame = new Frame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new MainPanel());
        frame.setSize(700, 600);
        frame.setVisible(true);
    }
}
