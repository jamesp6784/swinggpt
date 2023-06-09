package gwg6784.swinggpt.gui.chat;

import gwg6784.swinggpt.gui.common.Panel;
import gwg6784.swinggpt.gui.common.ScrollPane;
import gwg6784.swinggpt.services.Services;
import gwg6784.swinggpt.services.WindowService;
import gwg6784.swinggpt.services.conversation.ConversationService;
import gwg6784.swinggpt.services.conversation.models.Conversation;
import gwg6784.swinggpt.services.conversation.models.ConversationEntry;

import java.awt.BorderLayout;
import java.util.concurrent.CompletableFuture;

public class ChatPanel extends Panel {
    private ConversationService conversationService = Services.get(ConversationService.class);
    private WindowService titleService = Services.get(WindowService.class);
    private Conversation conversation;

    private MessagePanel messagePanel = new MessagePanel();

    public ChatPanel(Conversation conversation) {
        setLayout(new BorderLayout());

        if (conversation != null) {
            this.setConversation(conversation);
            for (ConversationEntry entry : this.conversationService.getConversationHistory(conversation.id)) {
                this.messagePanel.addMessage(entry.prompt, false);
                this.messagePanel.addMessage(entry.reply, false);
            }
        } else {
            this.titleService.setTitle("New chat");
        }

        add(new ScrollPane(this.messagePanel), BorderLayout.CENTER);
        add(new ScrollPane(new ChatInputPanel(prompt -> this.onSubmit(prompt))), BorderLayout.SOUTH);

        // TEMP
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
        this.messagePanel.addMessage("asd", false);
    }

    public ChatPanel() {
        this(null);
    }

    private void setConversation(Conversation conversation) {
        this.conversation = conversation;
        this.titleService.setTitle(conversation.name);
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
