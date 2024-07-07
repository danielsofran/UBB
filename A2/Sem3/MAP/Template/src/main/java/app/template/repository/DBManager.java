package app.template.repository;

import app.template.orm.ConnectionManager;
import app.template.orm.ORM;
import app.template.orm.exceptions.OrmException;

import java.sql.SQLException;

public class DBManager {
    public static void main(String[] args) throws OrmException, SQLException {
        String dbName = "MAPExamen";
        ConnectionManager connectionManager = new ConnectionManager(dbName);
        ORM orm = new ORM(connectionManager);

    }
}
