package gwg6784.swinggpt.gui;

import java.awt.BorderLayout;

import gwg6784.swinggpt.gui.chat.ChatPanel;
import gwg6784.swinggpt.gui.common.Panel;
import gwg6784.swinggpt.gui.common.Slot;
import gwg6784.swinggpt.gui.sidebar.SidebarPanel;
import gwg6784.swinggpt.services.Services;
import gwg6784.swinggpt.services.conversation.ConversationService;
import gwg6784.swinggpt.services.conversation.models.Conversation;

public class MainPanel extends Panel {
    private static final int KEY_NEW_CHAT = 1;
    private static final int KEY_CLEAR_CHATS = 2;

    private ConversationService conversationService = Services.get(ConversationService.class);

    private SidebarPanel sidebarPanel = new SidebarPanel();
    private Slot contentSlot = new Slot(new WelcomePanel());

    public MainPanel() {
        setLayout(new BorderLayout());

        this.updateSidebar();

        add(this.sidebarPanel, BorderLayout.WEST);
        add(this.contentSlot, BorderLayout.CENTER);

        this.conversationService.addConversationListListener(() -> this.updateSidebar());
    }

    private void updateSidebar() {
        this.sidebarPanel.clearItems();

        this.sidebarPanel.addItem(KEY_NEW_CHAT, "New chat", () -> this.newChat());

        for (Conversation conv : this.conversationService.getConversations()) {
            this.sidebarPanel.addItem(conv.id, conv.name, () -> this.contentSlot.set(new ChatPanel(conv)));
        }

        this.sidebarPanel.addItem(KEY_CLEAR_CHATS, "Clear history", () -> {
            this.conversationService.deleteAllConversations();
            this.newChat();
        });
    }

    private void newChat() {
        this.contentSlot.set(new ChatPanel());
    }
}
