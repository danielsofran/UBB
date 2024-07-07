package app.template.repository;

import app.template.models.Client;
import app.template.models.Flight;
import app.template.models.Ticket;
import app.template.orm.ConnectionManager;
import app.template.orm.ORM;
import app.template.orm.exceptions.OrmException;

import java.sql.SQLException;

public class DBManager {
    public static void main(String[] args) throws OrmException, SQLException {
        String dbName = "MAPExamen";
        ConnectionManager connectionManager = new ConnectionManager(dbName);
        ORM orm = new ORM(connectionManager);
        orm.createTables(Client.class);
        orm.createTables(Flight.class);
        orm.createTables(Ticket.class);

        orm.dropTables(Client.class);
        orm.dropTables(Flight.class);
        orm.dropTables(Ticket.class);

        populate(orm);
    }

    static void populate(ORM orm) throws OrmException, SQLException {
        Client client = new Client();
        client.setNume("Nume");
        client.setUsername("Username");

        client = orm.insert(client);
        client = orm.select(Client.class, client.getUsername());
        client.setNume("NUme2");
        orm.update(client);
        orm.delete(Client.class, client);
    }
}
