// Written by James P. (21154854)

package gwg6784.swinggpt.api.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gwg6784.swinggpt.conversation.ConversationEntry;

/**
 * A chat request as required by the OpenAI API
 */
public class ChatRequest {
    public final String model;
    public final List<ChatMessage> messages;

    private ChatRequest(String model, List<ChatMessage> messages) {
        this.model = model;
        this.messages = Collections.unmodifiableList(messages);
    }

    public static ChatRequest withHistory(String model, String prompt, List<ConversationEntry> entries) {
        List<ChatMessage> messages = new ArrayList<>();

        if (entries != null) {
            for (ConversationEntry entry : entries) {
                messages.add(new ChatMessage("user", entry.prompt));
                messages.add(new ChatMessage("assistant", entry.reply));
            }
        }

        messages.add(new ChatMessage("user", prompt));

        return new ChatRequest(model, messages);
    }

    public static ChatRequest empty(String model, String prompt) {
        return withHistory(model, prompt, null);
    }
}
