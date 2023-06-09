package gwg6784.swinggpt.gui;

import java.awt.BorderLayout;
import java.util.List;
import java.util.UUID;

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

    private UUID currentConvId;

    public MainPanel() {
        setLayout(new BorderLayout());

        add(this.sidebarPanel, BorderLayout.WEST);
        add(this.contentSlot, BorderLayout.CENTER);

        this.updateSidebar();

        this.conversationService.addConversationListListener(() -> this.updateSidebar());
    }

    private void updateSidebar() {
        this.sidebarPanel.clearItems();

        this.sidebarPanel.addItem(KEY_NEW_CHAT, "+   New chat", () -> this.openChat(null));
        this.sidebarPanel.addDivider();

        List<Conversation> conversations = this.conversationService.getConversations();

        for (Conversation conv : conversations) {
            this.sidebarPanel.addItem(conv.id, conv.name,
                    () -> this.openChat(conv),
                    () -> this.deleteChat(conv.id));
        }

        if (!conversations.isEmpty()) {
            this.sidebarPanel.addDivider();
            this.sidebarPanel.addItem(KEY_CLEAR_CHATS, "Clear history", () -> {
                this.conversationService.deleteAllConversations();
                this.openChat(null);
            });
        }
    }

    private void openChat(Conversation conv) {
        this.currentConvId = conv != null ? conv.id : null;
        this.contentSlot.set(new ChatPanel(conv));
    }

    private void deleteChat(UUID id) {
        this.conversationService.deleteConversation(id);

        if (this.currentConvId != null && this.currentConvId.equals(id)) {
            this.openChat(null);
        }
    }
}
