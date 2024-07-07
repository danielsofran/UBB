package app.template.repository;

import app.template.models.MenuItem;
import app.template.models.Order;
import app.template.models.OrderItems;
import app.template.models.Table;
import app.template.orm.ConnectionManager;
import app.template.orm.ORM;
import app.template.orm.exceptions.OrmException;

import java.sql.SQLException;

public class DBManager {
    public static void main(String[] args) throws OrmException, SQLException {
        String dbName = "MAP_ComenziRestaurante";
        ConnectionManager connectionManager = new ConnectionManager(dbName);
        ORM orm = new ORM(connectionManager);
//        orm.dropTables(Table.class, MenuItem.class);
//        orm.createTables(Table.class, MenuItem.class);
//        orm.dropTables(Order.class, OrderItems.class);
//        orm.createTables(Order.class, OrderItems.class);
    }
}
