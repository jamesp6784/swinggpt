// Written by James P. (21154854)

package gwg6784.swinggpt.api.models;

/**
 * A chat message as represented by the OpenAI API
 */
public class ChatMessage {
    public final String role;
    public final String content;

    public ChatMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }
}
