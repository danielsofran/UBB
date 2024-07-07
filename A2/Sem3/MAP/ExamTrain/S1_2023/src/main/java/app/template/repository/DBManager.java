package app.template.repository;

import app.template.models.*;
import app.template.orm.ConnectionManager;
import app.template.orm.ORM;
import app.template.orm.exceptions.OrmException;

import java.sql.SQLException;
import java.time.LocalDate;

public class DBManager {
    public static void main(String[] args) throws OrmException, SQLException {
        String dbName = "MAPS1_2022";
        ConnectionManager connectionManager = new ConnectionManager(dbName);
        ORM orm = new ORM(connectionManager);
        try {
//            orm.dropTables(Location.class, Hotel.class);
//            orm.createTables(Location.class, Hotel.class);
//            orm.createTables(SpecialOffer.class);
//            orm.createTables(Client.class);
            orm.createTables(Reservation.class);
            populate(orm);
        }
        catch (Exception ignored){}
    }

    static void populate(ORM orm) throws OrmException, SQLException {
//        Location l1 = new Location(1, "Romania");
//        Location l2 = new Location(2, "Austria");
//        Hotel h1 = new Hotel(1, 1, "H1", 10, 100, HotelType.family);
//        Hotel h2 = new Hotel(2, 1, "H2", 20, 200, HotelType.oldpeople);
//        Hotel h3 = new Hotel(3, 2, "H3", 30, 300, HotelType.teenagers);
//        Hotel h4 = new Hotel(4, 2, "H4", 40, 400, HotelType.family);
//        orm.insert(l1);
//        orm.insert(l2);
//        orm.insert(h1);
//        orm.insert(h2);
//        orm.insert(h3);
//        orm.insert(h4);
//        SpecialOffer s1 = new SpecialOffer(1, 1, LocalDate.of(2022, 10, 10),
//                                                LocalDate.of(2022, 11, 10), 30);
//        SpecialOffer s2 = new SpecialOffer(2, 1, LocalDate.of(2022, 11, 10),
//                LocalDate.of(2022, 12, 10), 50);
//        SpecialOffer s3 = new SpecialOffer(3, 2, LocalDate.of(2022, 07, 10),
//                LocalDate.of(2022, 11, 10), 70);
//        orm.insert(s1);
//        orm.insert(s2);
//        orm.insert(s3);
//        Client c1 = new Client(1L, "C1", 60, 20, Hobbies.extreme_sports);
//        Client c2 = new Client(2L, "C2", 40, 30, Hobbies.extreme_sports);
//        orm.insert(c1);
//        orm.insert(c2);
//        SpecialOffer su1 = new SpecialOffer(4, 1, LocalDate.of(2023, 01, 10),
//                LocalDate.of(2023, 03, 1), 20);
//        SpecialOffer su2 = new SpecialOffer(5, 1, LocalDate.of(2023, 01, 10),
//                LocalDate.of(2023, 03, 1), 90);
//        orm.insert(su1);
//        orm.insert(su2);
    }
}
