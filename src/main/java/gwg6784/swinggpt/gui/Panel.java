package gwg6784.swinggpt.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gwg6784.swinggpt.conversation.Conversation;
import gwg6784.swinggpt.conversation.ConversationService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.concurrent.CompletableFuture;

public class Panel extends JPanel {
    private MessagePanel messagePanel = new MessagePanel();
    private ConversationService conversationService = ConversationService.getInstance();
    private Conversation conversation;
    private JLabel topLabel = new JLabel("New chat");

    public Panel(Conversation conversation) {
        this();
        setConversation(conversation);
    }

    public Panel() {
        setBackground(Color.GRAY);

        setLayout(new BorderLayout());

        add(this.topLabel, BorderLayout.NORTH);
        add(new JScrollPane(this.messagePanel), BorderLayout.CENTER);
        add(new JScrollPane(new ChatInputPanel(prompt -> this.onSubmit(prompt))), BorderLayout.SOUTH);
    }

    private void setConversation(Conversation conversation) {
        this.conversation = conversation;
        this.topLabel.setText(conversation.name);
    }

    private void onSubmit(String prompt) {
        this.messagePanel.addMessage(prompt, false);

        CompletableFuture<String> replyFut;

        if (this.conversation == null) {
            replyFut = this.conversationService.newConversation(prompt).thenApply(res -> {
                this.setConversation(res.getValue0());
                return res.getValue1();
            });
        } else {
            replyFut = this.conversationService.continueConversation(this.conversation.id, prompt);
        }

        replyFut.thenAccept(reply -> {
            messagePanel.addMessage(reply, true);
        }).exceptionally(e -> {
            e.printStackTrace();
            // TODO: handle
            return null;
        });
    }
}
