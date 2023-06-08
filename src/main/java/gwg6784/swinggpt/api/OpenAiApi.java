// Written by James P. (21154854)

package gwg6784.swinggpt.api;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import gwg6784.swinggpt.api.models.ChatRequest;
import gwg6784.swinggpt.api.models.ChatResponse;
import gwg6784.swinggpt.conversation.ConversationEntry;

public final class OpenAiApi {
    private static final String ENDPOINT = "https://api.openai.com/v1/chat/completions";
    private static final String BEARER_TOKEN = System.getenv("OPENAI_TOKEN");
    private static final String MODEL = "gpt-3.5-turbo";

    private static final Gson gson = new Gson();

    private OpenAiApi() {
    }

    public static String sendRequest(ChatRequest req) throws IOException {
        String body = gson.toJson(req);

        StringEntity entity = new StringEntity(body, ContentType.APPLICATION_JSON);

        Content content = Request.post(ENDPOINT).body(entity).addHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .execute().returnContent();

        ChatResponse res = gson.fromJson(new JsonReader(new InputStreamReader(content.asStream())), ChatResponse.class);

        return res.choices.get(0).message.content;
    }

    /**
     * Continues a chat conversation with a prompt and chat history.
     *
     * @param prompt  the prompt
     * @param history the conversation history
     * @return the response from OpenAI
     * @throws IOException
     */
    public static String chat(String prompt, List<ConversationEntry> history) throws IOException {
        return sendRequest(ChatRequest.withHistory(MODEL, prompt, history));
    }

    /**
     * Creates a new conversation with a prompt.
     *
     * @param prompt the prompt
     * @return the response from OpenAI
     * @throws IOException
     */
    public static String chat(String prompt) throws IOException {
        return sendRequest(ChatRequest.empty(MODEL, prompt));
    }
}
