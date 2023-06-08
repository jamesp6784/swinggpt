package gwg6784.swinggpt;

import javax.swing.JFrame;

import gwg6784.swinggpt.gui.Panel;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("SwingGPT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Panel panel = new Panel();
        frame.add(panel);
        frame.setSize(700, 600);
        frame.setVisible(true);
    }
}
