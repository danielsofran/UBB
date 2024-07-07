package app.template.repository;

import app.template.models.Localitate;
import app.template.models.Rau;
import app.template.orm.ConnectionManager;
import app.template.orm.ORM;
import app.template.orm.exceptions.OrmException;

import java.sql.SQLException;

public class DBManager {
    public static void main(String[] args) throws OrmException, SQLException {
        String dbName = "MAP_ANAR";
        ConnectionManager connectionManager = new ConnectionManager(dbName);
        ORM orm = new ORM(connectionManager);
        orm.createTables(Rau.class);
        orm.createTables(Localitate.class);
        orm.dropTables(Rau.class);
        orm.dropTables(Localitate.class);
    }
}
