package gwg6784.swinggpt.services.conversation.models;

public class ConversationEntry {
    public final String prompt;
    public final String reply;

    public ConversationEntry(String prompt, String reply) {
        this.prompt = prompt;
        this.reply = reply;
    }
}
