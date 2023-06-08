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
import javax.swing.Timer;

import gwg6784.swinggpt.conversation.ConversationService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

public class Panel extends JPanel {
    private MessagePanel messagePanel = new MessagePanel();
    private ConversationService conversationService = ConversationService.getInstance();

    public Panel() {

        setBackground(Color.GRAY);

        setLayout(new BorderLayout());

        add(new JScrollPane(this.messagePanel), BorderLayout.CENTER);
        add(new JScrollPane(new ChatInputPanel(prompt -> this.onSubmit(prompt))),
                BorderLayout.SOUTH);

        messagePanel.addMessage(
                "Have you ever wondered why flamingos stand on one leg? These majestic birds exhibit this peculiar behavior to conserve body heat. By tucking one leg close to their body, flamingos reduce the amount of exposed surface area, minimizing heat loss. This posture also helps them maintain balance and stability on uneven surfaces such as mudflats and shallow waters where they often feed.",
                true);

    }

    private void onSubmit(String prompt) {
        System.out.println(prompt);
    }
}
