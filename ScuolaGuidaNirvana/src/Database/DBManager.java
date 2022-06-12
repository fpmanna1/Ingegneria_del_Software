package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager
{
    private static final String URL = "jdbc:h2:~/GitHub/Java/canale_j_z-nirvana/ScuolaGuidaNirvana/lib/database";
    private static Connection connection = null;

    private DBManager(){}

    public static Connection getConnection() throws SQLException
    {
        if(connection == null || connection.isClosed()){
            connection = DriverManager.getConnection(URL, "sa", "");
        }
        return connection;
    }

    public static void closeConnection() throws SQLException
    {
        if(connection != null)
            connection.close();
    }
}
