package gwg6784.swinggpt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import gwg6784.swinggpt.Util;
import gwg6784.swinggpt.services.conversation.models.Conversation;
import gwg6784.swinggpt.services.conversation.models.ConversationEntry;

public class Database {
    private static final String CONNECTION_STRING = "jdbc:derby:memory:swinggpt;create=true";
    private static final String STATUS_TABLE_ALREADY_EXISTS = "X0Y32";
    private static final DateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

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
        return this.query("SELECT * FROM conversations ORDER BY ts DESC",
                rs -> new Conversation(
                        Util.uuidFromBytes(rs.getBytes("id")),
                        rs.getString("name"),
                        parseSqlDateString(rs.getString("ts"))));
    }

    public List<ConversationEntry> getConversationHistory(UUID id) throws SQLException {
        return this.query("SELECT * FROM entries WHERE conversation_id = ? ORDER BY ts",
                rs -> new ConversationEntry(
                        rs.getString("prompt"),
                        rs.getString("reply"),
                        parseSqlDateString(rs.getString("ts"))),
                id);
    }

    public UUID createConversation(String name, Date timestamp) throws SQLException {
        UUID id = UUID.randomUUID();
        this.update("INSERT INTO conversations VALUES (?, ?, ?)", id, name, timestamp);
        return id;
    }

    public UUID createEntry(UUID conversationId, String prompt, String reply, Date timestamp) throws SQLException {
        UUID id = UUID.randomUUID();
        this.update("INSERT INTO entries VALUES (?, ?, ?, ?, ?)", id, conversationId, prompt, reply, timestamp);
        return id;
    }

    public void deleteConversation(UUID id) throws SQLException {
        this.update("DELETE FROM conversations WHERE id = ?", id);
    }

    public void deleteAllConversations() throws SQLException {
        this.update("DELETE FROM conversations");
    }

    private void ensureTablesCreated() throws SQLException {
        this.ensureTableCreated(
                "CREATE TABLE conversations (id CHAR(16) FOR BIT DATA PRIMARY KEY, name VARCHAR(128), ts TIMESTAMP)");
        this.ensureTableCreated(
                "CREATE TABLE entries (id CHAR(16) FOR BIT DATA PRIMARY KEY, conversation_id CHAR(16) FOR BIT DATA REFERENCES conversations(id) ON DELETE CASCADE, prompt VARCHAR(1000), reply VARCHAR(4000), ts TIMESTAMP)");
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
            } else if (param instanceof Date) {
                stmt.setString(i + 1, SQL_DATE_FORMAT.format((Date) param));
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

    private static Date parseSqlDateString(String str) {
        try {
            return SQL_DATE_FORMAT.parse(str);
        } catch (ParseException e) {
            return new Date(0);
        }
    }
}
