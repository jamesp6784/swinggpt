// Written by James P. (21154854)

package gwg6784.swinggpt.conversation;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.google.gson.reflect.TypeToken;

import gwg6784.swinggpt.api.OpenAiApi;
import gwg6784.swinggpt.api.models.ChatResponse;

/**
 * Service for getting, creating and deleting conversations.
 */
public class ConversationService {
    private static final ConversationService INSTANCE = new ConversationService();

    public static ConversationService getInstance() {
        return INSTANCE;
    }

    public CompletableFuture<Conversation> newConversation(String prompt) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                ChatResponse res = OpenAiApi.chat(prompt);

                ArrayList<Conversation.Entry> entries = new ArrayList<>();
                entries.add(new Conversation.Entry(prompt,
                        res.choices.get(0).message.content));

                UUID id = UUID.randomUUID();

                Conversation conv = new Conversation(id, "Chat", entries);

                return conv;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // public CompletableFuture<UUID> continueConversation(UUID conversationId,
    // String prompt) {
    // return CompletableFuture.supplyAsync(() -> {
    // try {
    // Conversation conv = this.file.getData().get(conversationId);

    // if (conv == null) {
    // throw new IllegalStateException("Tried to continue a missing conversation");
    // }

    // ChatResponse res = OpenAiApi.chat(prompt, conv);

    // Conversation.Entry entry = new Conversation.Entry(prompt,
    // res.choices.get(0).message.content);

    // this.file.getData().get(conversationId).entries.add(entry);
    // this.trySaveFile();

    // return conversationId;
    // } catch (IOException e) {
    // throw new RuntimeException(e);
    // }
    // });
    // }
}
