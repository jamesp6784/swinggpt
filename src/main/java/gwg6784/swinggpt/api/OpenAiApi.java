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

import gwg6784.swinggpt.api.models.ChatMessage;
import gwg6784.swinggpt.api.models.ChatRequest;
import gwg6784.swinggpt.api.models.ChatResponse;

public final class OpenAiApi {
    private final static String ENDPOINT = "https://api.openai.com/v1/chat/completions";
    private final static String BEARER_TOKEN = System.getenv("OPENAI_TOKEN");
    private final static String MODEL = "gpt-3.5-turbo";

    private final static Gson gson = new Gson();

    private OpenAiApi() {
    }

    public static ChatResponse sendRequest(ChatRequest req) throws IOException {
        String body = gson.toJson(req);

        StringEntity entity = new StringEntity(body, ContentType.APPLICATION_JSON);

        Content res = Request.post(ENDPOINT).body(entity).addHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .execute().returnContent();

        return gson.fromJson(new JsonReader(new InputStreamReader(res.asStream())), ChatResponse.class);
    }

    /**
     * Continues a chat conversation with a prompt and chat history.
     *
     * @param prompt  the prompt
     * @param history the conversation history
     * @return the response from OpenAI
     * @throws IOException
     */
    public static ChatResponse chat(String prompt, List<ChatMessage> history) throws IOException {
        return sendRequest(ChatRequest.withHistory(MODEL, prompt, history));
    }

    /**
     * Creates a new conversation with a prompt.
     *
     * @param prompt the prompt
     * @return the response from OpenAI
     * @throws IOException
     */
    public static ChatResponse chat(String prompt) throws IOException {
        return sendRequest(ChatRequest.empty(MODEL, prompt));
    }
}
