// Written by James P. (21154854)

package gwg6784.swinggpt.conversation;

import java.util.UUID;

/**
 * A representation of a conversation
 */
public class Conversation {
    public final UUID id;
    public final String name;

    public Conversation(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
