package gwg6784.swinggpt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String CONNECTION_STRING = "jdbc:derby:memory:demo;create=true";
    private static final String STATUS_TABLE_ALREADY_EXISTS = "X0Y32";
    private static final Database INSTANCE = connect();

    private Connection conn;

    private Database(Connection conn) {
        this.conn = conn;
    }

    private static Database connect() {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Database database = new Database(conn);
            database.ensureTablesCreated();
            return database;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void ensureTablesCreated() throws SQLException {
        this.ensureTableCreated("CREATE TABLE TESTTABLE (id int primary key, name varchar(20))");
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

    private void update(String sql) throws SQLException {
        this.conn.createStatement().executeUpdate(sql);
    }

    public static Database getInstance() {
        return INSTANCE;
    }
}
