package app.template.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionManager {
    private String url; // jdbc:postgresql://localhost:5432/TestORM
    private final String username;
    private final String password;
    private final String port;

    public ConnectionManager(String database){
        username = "postgres";
        password = "parola";
        port = "5432";
        setDatabase(database);
    }

    public void setDatabase(String database)
    {
        url = "jdbc:postgresql://localhost:"+port+"/"+database;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public void executeUpdateSql(String sql) throws SQLException {
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.executeUpdate();
        }
    }
}
