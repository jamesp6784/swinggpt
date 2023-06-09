// Written by James P. (21154854)

package gwg6784.swinggpt.db;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultMapper<T> {
    public T map(ResultSet rs) throws SQLException;
}
