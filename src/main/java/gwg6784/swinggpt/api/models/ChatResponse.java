// Written by James P. (21154854)

package gwg6784.swinggpt.api.models;

import java.util.Collections;
import java.util.List;

/**
 * A chat response as represented by the OpenAI API
 */
public class ChatResponse {
    public final List<Choice> choices;

    public ChatResponse(List<Choice> choices) {
        this.choices = Collections.unmodifiableList(choices);
    }

    public static class Choice {
        public final ChatMessage message;

        public Choice(ChatMessage message) {
            this.message = message;
        }
    }
}
