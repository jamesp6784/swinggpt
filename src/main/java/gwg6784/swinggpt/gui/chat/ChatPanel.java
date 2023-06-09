// Written by James P. (21154854)

package gwg6784.swinggpt.gui.chat;

import gwg6784.swinggpt.gui.Palette;
import gwg6784.swinggpt.gui.common.Panel;
import gwg6784.swinggpt.gui.common.ScrollPane;
import gwg6784.swinggpt.services.Services;
import gwg6784.swinggpt.services.conversation.ConversationService;
import gwg6784.swinggpt.services.conversation.models.Conversation;
import gwg6784.swinggpt.services.conversation.models.ConversationEntry;

import java.awt.BorderLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.CompletableFuture;

public class ChatPanel extends Panel {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yy  hh:mma");

    private final ConversationService conversationService = Services.get(ConversationService.class);
    private Conversation conversation;

    private final ChatHeaderPanel headerPanel = new ChatHeaderPanel("New chat", "Enter a prompt into the box below.");
    private final MessagePanel messagePanel = new MessagePanel();
    private final ChatInputPanel chatInputPanel = new ChatInputPanel(prompt -> this.onSubmit(prompt));

    public ChatPanel(Conversation conversation) {
        setLayout(new BorderLayout());

        setBackground(Palette.BACKGROUND_2);

        if (conversation != null) {
            this.setConversation(conversation);
            for (ConversationEntry entry : this.conversationService.getConversationHistory(conversation.id)) {
                this.messagePanel.addPrompt(entry.prompt);
                this.messagePanel.addReply(entry.reply, false);
            }
        }

        add(this.headerPanel, BorderLayout.NORTH);
        add(new ScrollPane(this.messagePanel), BorderLayout.CENTER);
        add(new ScrollPane(this.chatInputPanel), BorderLayout.SOUTH);
    }

    public ChatPanel() {
        this(null);
    }

    private void setConversation(Conversation conversation) {
        this.conversation = conversation;
        this.headerPanel.setHeader(conversation.name);
        this.headerPanel.setSubtext(DATE_FORMAT.format(conversation.timestamp));
    }

    private void onSubmit(String prompt) {
        if (prompt.trim().isEmpty() || prompt.length() > 1000 || this.messagePanel.getLoading()) {
            return;
        }

        this.chatInputPanel.clear();

        this.messagePanel.addPrompt(prompt);
        this.messagePanel.setLoading();

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
            this.messagePanel.addReply(reply, true);
        }).exceptionally(e -> {
            this.messagePanel.addError("An error occured: " + e.getMessage());
            return null;
        });
    }
}
