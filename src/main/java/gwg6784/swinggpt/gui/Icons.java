package gwg6784.swinggpt.gui;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public final class Icons {
    public Icons() {
    }

    public static ImageIcon LOGO_ICON = loadIcon("icons/logo.png");
    public static ImageIcon CLOSE_ICON = loadIcon("icons/close.png");
    public static ImageIcon MINIMIZE_ICON = loadIcon("icons/minimize.png");
    public static ImageIcon DELETE_ICON = loadIcon("icons/delete.png");
    public static ImageIcon LOADER_ICON = loadIcon("icons/loader.gif");

    public static Image LOGO_IMG = loadImage("icons/big.png");

    private static ImageIcon loadIcon(String file) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(file);
        return new ImageIcon(url);
    }

    private static Image loadImage(String file) {
        return loadIcon(file).getImage();
    }
}
