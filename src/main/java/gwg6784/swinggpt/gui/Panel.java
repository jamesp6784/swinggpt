package gwg6784.swinggpt.gui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

public class Panel extends JPanel {
    MessagePanel messagePanel = new MessagePanel();

    public Panel() {

        setBackground(Color.GRAY);

        setLayout(new BorderLayout());

        JScrollPane jsp = new JScrollPane(this.messagePanel);

        add(jsp, BorderLayout.CENTER);
        add(new JLabel("hello"), BorderLayout.SOUTH);

    }

}
