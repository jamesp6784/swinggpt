package gwg6784.swinggpt.gui.frame;

import javax.swing.JFrame;

import gwg6784.swinggpt.services.Services;
import gwg6784.swinggpt.services.TitleService;

public class Frame extends JFrame {
    private TitleService titleService = Services.get(TitleService.class);

    public Frame() {
        setTitle(titleService.getTitle());
        titleService.addObserver(t -> setTitle(t));
    }
}
