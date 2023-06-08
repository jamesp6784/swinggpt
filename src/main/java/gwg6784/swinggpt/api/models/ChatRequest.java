// Written by James P. (21154854)

package gwg6784.swinggpt.api.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public static ChatRequest withHistory(String model, String prompt, List<ChatMessage> history) {
        List<ChatMessage> messages = new ArrayList<>(history);

        messages.add(new ChatMessage("user", prompt));

        return new ChatRequest(model, messages);
    }

    public static ChatRequest empty(String model, String prompt) {
        return withHistory(model, prompt, null);
    }
}
