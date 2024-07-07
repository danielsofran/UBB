import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class CarsDBRepository implements CarRepository{

    private JdbcUtils dbUtils;



    private static final Logger logger= LogManager.getLogger();

    public CarsDBRepository(Properties props) {
        logger.info("Initializing CarsDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public List<Car> findByManufacturer(String manufacturerN) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Car> cars = new LinkedList<>();
        try(PreparedStatement ps=con.prepareStatement("select * from cars where manufacturer=?")){
            ps.setString(1,manufacturerN);
            getCars(cars, ps);
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB " + e);
        }
        logger.traceExit();
        return cars;
    }

    @Override
    public List<Car> findBetweenYears(int min, int max) {
	    logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Car> cars = new LinkedList<>();
        try(PreparedStatement ps=con.prepareStatement("select * from cars where year between ? and ?")){
            ps.setInt(1,min);
            ps.setInt(2,max);
            getCars(cars, ps);
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB " + e);
        }
        logger.traceExit();
        return cars;
    }

    private void getCars(List<Car> cars, PreparedStatement ps) throws SQLException {
        try(ResultSet rs=ps.executeQuery()){
            while (rs.next())
            {
                int id = rs.getInt("id");
                String manufacturer = rs.getString("manufacturer");
                String model = rs.getString("model");
                int year = rs.getInt("year");
                Car car = new Car(manufacturer, model, year);
                car.setId(id);
                cars.add(car);
            }
        }
    }

    @Override
    public void add(Car elem) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        try (PreparedStatement ps = con.prepareStatement("insert into cars(manufacturer, model, year) values (?,?,?)")) {
            //ps.setInt(1, elem.getId());
            ps.setString(1, elem.getManufacturer());
            ps.setString(2, elem.getModel());
            ps.setInt(3, elem.getYear());
            int result = ps.executeUpdate();
            logger.trace("Saved {} instances", result);
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB " + e);
        }
        logger.traceExit();
    }

        @Override
    public void update(Integer id, Car elem) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        try (PreparedStatement ps = con.prepareStatement("update cars set manufacturer=?, model=?, year=? where id=?")) {
            ps.setString(1, elem.getManufacturer());
            ps.setString(2, elem.getModel());
            ps.setInt(3, elem.getYear());
            ps.setInt(4, id);
            int result = ps.executeUpdate();
            logger.trace("Updated {} instances", result);
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB " + e);
        }
        logger.traceExit();
    }

    @Override
    public Iterable<Car> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Car> cars = new LinkedList<>();
        try(PreparedStatement ps = con.prepareStatement("select * from cars")){
            getCars(cars, ps);
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB " + e);
        }
        logger.traceExit(cars);
        return cars;
    }
}
