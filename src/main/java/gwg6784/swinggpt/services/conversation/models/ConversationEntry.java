// Written by James P. (21154854)

package gwg6784.swinggpt.services.conversation.models;

import java.util.Date;

public class ConversationEntry {
    public final String prompt;
    public final String reply;
    public final Date timestamp;

    public ConversationEntry(String prompt, String reply, Date timestamp) {
        this.prompt = prompt;
        this.reply = reply;
        this.timestamp = timestamp;
    }
}
