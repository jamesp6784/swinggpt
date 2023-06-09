// Written by James P. (21154854)

package gwg6784.swinggpt.services.conversation;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.javatuples.Pair;

import gwg6784.swinggpt.api.OpenAiApi;
import gwg6784.swinggpt.db.Database;
import gwg6784.swinggpt.services.conversation.models.Conversation;
import gwg6784.swinggpt.services.conversation.models.ConversationEntry;

/**
 * Service for getting, creating and deleting conversations.
 */
public class ConversationService {
    /// A prompt for OpenAI to generate a title for a conversation
    private static final String TITLE_PROMPT_TEMPLATE = "---BEGIN CONVERSATION---\n%s\n---END CONVERSATION---\nSummarize the conversation in 5 words or less";

    private final Database db = Database.connect();
    private final Set<Runnable> conversationListObservers = new HashSet<>();

    public ConversationService() {
    }

    /**
     * Create a new conversation
     */
    public CompletableFuture<Pair<Conversation, String>> newConversation(String prompt) {
        // Future for chatbot reply
        CompletableFuture<String> replyFuture = CompletableFuture
                .supplyAsync((ThrowableSupplier<String>) () -> OpenAiApi.chat(prompt));

        // Future for conversation title generation reply
        CompletableFuture<String> titleFuture = CompletableFuture
                .supplyAsync((ThrowableSupplier<String>) () -> OpenAiApi.chat(createTitlePrompt(prompt)));

        // Future stage when both futures complete
        ThrowableBiFunction<String, String, Pair<Conversation, String>> bothRequestsFinished = (reply, title) -> {
            Date date = new Date(new java.util.Date().getTime());
            UUID conversationId = this.db.createConversation(title, date);
            this.db.createEntry(conversationId, prompt, reply, date);

            Conversation conv = new Conversation(conversationId, title, date);
            this.notifyListListeners();

            // Return both new conversation and the reply
            return new Pair<>(conv, reply);
        };

        return replyFuture.thenCombine(titleFuture, bothRequestsFinished);
    }

    /**
     * Continue an existing conversation
     */
    public CompletableFuture<String> continueConversation(UUID conversationId, String prompt) {
        ThrowableSupplier<String> supplier = () -> {
            List<ConversationEntry> conv = this.db.getConversationHistory(conversationId);

            if (conv.isEmpty()) {
                throw new IllegalStateException("Tried to continue a missing conversation");
            }

            String reply = OpenAiApi.chat(prompt, conv);

            Date date = new Date(new java.util.Date().getTime());
            this.db.createEntry(conversationId, prompt, reply, date);

            return reply;
        };

        return CompletableFuture.supplyAsync(supplier);
    }

    public List<Conversation> getConversations() {
        return this.executeOrPanic((ThrowableSupplier<List<Conversation>>) () -> this.db.getConversations());
    }

    public List<ConversationEntry> getConversationHistory(UUID id) {
        return this
                .executeOrPanic((ThrowableSupplier<List<ConversationEntry>>) () -> this.db.getConversationHistory(id));
    }

    /**
     * Delete a conversation
     */
    public void deleteConversation(UUID id) {
        this.executeOrPanic((ThrowableRunnable) () -> this.db.deleteConversation(id));
        this.notifyListListeners();
    }

    /**
     * Clear all conversations
     */
    public void deleteAllConversations() {
        this.executeOrPanic((ThrowableRunnable) () -> this.db.deleteAllConversations());
        this.notifyListListeners();
    }

    /**
     * Listen to changes in the conversation list
     */
    public void addConversationListListener(Runnable listener) {
        this.conversationListObservers.add(listener);
    }

    private <T> T executeOrPanic(ThrowableSupplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(0);
            return null;
        }
    }

    private void executeOrPanic(Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void notifyListListeners() {
        for (Runnable listener : this.conversationListObservers) {
            listener.run();
        }
    }

    private static String createTitlePrompt(String prompt) {
        return String.format(TITLE_PROMPT_TEMPLATE, prompt);
    }
}
