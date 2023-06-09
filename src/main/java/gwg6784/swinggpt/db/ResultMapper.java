// Written by James P. (21154854)

package gwg6784.swinggpt.db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * An interface to try map a ResultSet to type T
 */
@FunctionalInterface
public interface ResultMapper<T> {
    public T map(ResultSet rs) throws SQLException;
}
