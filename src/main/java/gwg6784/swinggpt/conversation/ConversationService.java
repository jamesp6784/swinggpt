// Written by James P. (21154854)

package gwg6784.swinggpt.conversation;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.javatuples.Pair;

import gwg6784.swinggpt.api.OpenAiApi;
import gwg6784.swinggpt.db.Database;

/**
 * Service for getting, creating and deleting conversations.
 */
public class ConversationService {
    private static final String TITLE_PROMPT_TEMPLATE = "---BEGIN Conversation---\n%s\n---END Conversation---\nSummarize the conversation in 5 words or less";

    private static final ConversationService INSTANCE = new ConversationService();

    private final Database db = Database.getInstance();

    public static ConversationService getInstance() {
        return INSTANCE;
    }

    public CompletableFuture<Pair<Conversation, String>> newConversation(String prompt) {
        CompletableFuture<String> replyFuture = CompletableFuture
                .supplyAsync((ThrowableSupplier<String>) () -> OpenAiApi.chat(prompt));

        CompletableFuture<String> titleFuture = CompletableFuture
                .supplyAsync((ThrowableSupplier<String>) () -> OpenAiApi
                        .chat(String.format(TITLE_PROMPT_TEMPLATE, prompt)));

        return replyFuture.thenCombine(titleFuture,
                (ThrowableBiFunction<String, String, Pair<Conversation, String>>) (reply, title) -> {

                    UUID conversationId = this.db.createConversation(title);
                    this.db.createEntry(conversationId, prompt, reply);

                    Conversation conv = new Conversation(conversationId, title);

                    return new Pair<>(conv, reply);
                });
    }

    public CompletableFuture<String> continueConversation(UUID conversationId, String prompt) {
        return CompletableFuture.supplyAsync((ThrowableSupplier<String>) () -> {
            List<ConversationEntry> conv = this.db.getConversationHistory(conversationId);

            if (conv.isEmpty()) {
                throw new IllegalStateException("Tried to continue a missing conversation");
            }

            String reply = OpenAiApi.chat(prompt, conv);

            this.db.createEntry(conversationId, prompt, reply);

            return reply;
        });
    }
}
