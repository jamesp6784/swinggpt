package gwg6784.swinggpt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import gwg6784.swinggpt.Util;
import gwg6784.swinggpt.services.conversation.models.Conversation;
import gwg6784.swinggpt.services.conversation.models.ConversationEntry;

public class Database {
    private static final String CONNECTION_STRING = "jdbc:derby:memory:demo;create=true";
    private static final String STATUS_TABLE_ALREADY_EXISTS = "X0Y32";

    private Connection conn;

    private Database(Connection conn) {
        this.conn = conn;
    }

    public static Database connect() {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Database database = new Database(conn);
            database.ensureTablesCreated();
            return database;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Conversation> getConversations() throws SQLException {
        return this.query("SELECT * FROM conversations",
                rs -> new Conversation(Util.uuidFromBytes(rs.getBytes("id")), rs.getString("name")));
    }

    public List<ConversationEntry> getConversationHistory(UUID id) throws SQLException {
        return this.query("SELECT * FROM entries WHERE conversation_id = ?",
                rs -> new ConversationEntry(rs.getString("prompt"), rs.getString("reply")), id);
    }

    public UUID createConversation(String name) throws SQLException {
        UUID id = UUID.randomUUID();
        this.update("INSERT INTO conversations VALUES (?, ?)", id, name);
        return id;
    }

    public UUID createEntry(UUID conversationId, String prompt, String reply) throws SQLException {
        UUID id = UUID.randomUUID();
        this.update("INSERT INTO entries VALUES (?, ?, ?, ?)", id, conversationId, prompt, reply);
        return id;
    }

    private void ensureTablesCreated() throws SQLException {
        this.ensureTableCreated("CREATE TABLE conversations (id CHAR(16) FOR BIT DATA PRIMARY KEY, name VARCHAR(128))");
        this.ensureTableCreated(
                "CREATE TABLE entries (id CHAR(16) FOR BIT DATA PRIMARY KEY, conversation_id CHAR(16) FOR BIT DATA REFERENCES conversations(id), prompt VARCHAR(1000), reply VARCHAR(4000))");
    }

    private void ensureTableCreated(String sql) throws SQLException {
        try {
            this.update(sql);
        } catch (SQLException e) {
            if (!e.getSQLState().equals(STATUS_TABLE_ALREADY_EXISTS)) {
                throw e;
            }
        }
    }

    private PreparedStatement prepareStatement(String sql, Object... params) throws SQLException {
        PreparedStatement stmt = this.conn.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            Object param = params[i];
            if (param instanceof UUID) {
                stmt.setBytes(i + 1, Util.uuidToBytes((UUID) param));
            } else {
                stmt.setObject(i + 1, params[i]);
            }
        }

        return stmt;
    }

    private void update(String sql, Object... params) throws SQLException {
        this.prepareStatement(sql, params).executeUpdate();
    }

    private <T> List<T> query(String sql, ResultMapper<T> mapFn, Object... params) throws SQLException {
        ResultSet rs = this.prepareStatement(sql, params).executeQuery();
        List<T> list = new ArrayList<>();

        while (rs.next()) {
            list.add(mapFn.map(rs));
        }

        return list;
    }
}
