// Written by James P. (21154854)

package gwg6784.swinggpt.services;

import javax.swing.JFrame;

/**
 * Allows control of the window from a service
 */
public class WindowService {
    private JFrame registeredFrame;

    public void registerFrame(JFrame frame) {
        this.registeredFrame = frame;
    }

    public void minimize() {
        this.registeredFrame.setState(JFrame.ICONIFIED);
    }

    public void close() {
        this.registeredFrame.dispose();
        this.registeredFrame = null;
    }

    public void setTitle(String title) {
        this.registeredFrame.setTitle(title);
    }
}
