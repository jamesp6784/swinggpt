// Written by James P. (21154854)

package gwg6784.swinggpt.services.conversation.models;

import java.util.Date;
import java.util.UUID;

/**
 * A representation of a conversation
 */
public class Conversation {
    public final UUID id;
    public final String name;
    public final Date timestamp;

    public Conversation(UUID id, String name, Date timestamp) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
    }
}
