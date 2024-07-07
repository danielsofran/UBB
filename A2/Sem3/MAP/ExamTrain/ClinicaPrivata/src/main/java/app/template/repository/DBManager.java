package app.template.repository;

import app.template.models.Consultatie;
import app.template.models.Medic;
import app.template.models.Sectie;
import app.template.orm.ConnectionManager;
import app.template.orm.ORM;
import app.template.orm.exceptions.OrmException;

import java.sql.SQLException;

public class DBManager {
    public static void main(String[] args) throws OrmException, SQLException {
        String dbName = "MAP_ClinicaPrivata";
        ConnectionManager connectionManager = new ConnectionManager(dbName);
        ORM orm = new ORM(connectionManager);
        //orm.createTables(Medic.class);
        //orm.createTables(Sectie.class);
        //orm.createTables(Consultatie.class);
    }
}
