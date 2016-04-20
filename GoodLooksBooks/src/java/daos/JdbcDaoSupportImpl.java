package daos;

import java.sql.Connection;

/* Instead of writing set&get connection methods in each dao we are writing once and re using the code
 * just like the dao Support class in Spring
 */
public class JdbcDaoSupportImpl implements JdbcDaoSupport {
    private Connection connection;

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}