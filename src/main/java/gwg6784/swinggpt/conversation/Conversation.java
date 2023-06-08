// Written by James P. (21154854)

package gwg6784.swinggpt.conversation;

import java.util.List;
import java.util.UUID;

/**
 * A representation of a conversation, with mutable entries.
 */
public class Conversation {
    public final UUID id;
    public final String name;
    public final List<Entry> entries;

    public Conversation(UUID id, String name, List<Entry> entries) {
        this.id = id;
        this.name = name;
        this.entries = entries;
    }

    public static class Entry {
        public final String prompt;
        public final String reply;

        public Entry(String prompt, String reply) {
            this.prompt = prompt;
            this.reply = reply;
        }
    }
}
