package repository.db;

import bilete.domain.Artist;
import bilete.domain.Show;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.ShowRepository;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Properties;

public class ShowRepoDB implements ShowRepository {
    private JdbcUtils jdbcUtils;
    private static final Logger logger= LogManager.getLogger();

    public ShowRepoDB(Properties props){
        jdbcUtils = new JdbcUtils(props);
    }


    @Override
    public int size() {
        logger.traceEntry("ShowRepo Size");
        Connection con = jdbcUtils.getConnection();
        int size = 0;
        try(PreparedStatement ps=con.prepareStatement("select count(*) from shows")){
            ResultSet rs = ps.executeQuery();
            rs.next();
            size = rs.getInt(1);
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error ShowDB " + e);
        }
        logger.traceExit();
        return size;
    }

    @Override
    public void save(Show entity) {
        logger.traceEntry("Saving show {} ", entity);
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement ps=con.prepareStatement("insert into shows(artist_id, date, begintime, location, availableseats, soldseats) values (?,?,?,?,?,?)")){
            ps.setInt(1, entity.getArtist().getId());
            ps.setDate(2, Date.valueOf(entity.getDate()));
            ps.setTime(3, Time.valueOf(entity.getBeginTime()));
            ps.setString(4, entity.getLocation());
            ps.setInt(5, entity.getAvailableSeats());
            ps.setInt(6, entity.getSoldSeats());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error ShowDB " + e);
        }
    }

    @Override
    public void delete(Integer integer) {
        logger.traceEntry("Deleting show with id {} ", integer);
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement ps=con.prepareStatement("delete from shows where id=?")){
            ps.setInt(1, integer);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error ShowDB " + e);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer integer, Show entity) {
        logger.traceEntry("Updating show with id {} ", integer);
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement ps=con.prepareStatement("update shows set artist_id=?, date=?, begintime=?, location=?, availableseats=?, soldseats=? where id=?")){
            ps.setInt(1, entity.getArtist().getId());
            ps.setDate(2, Date.valueOf(entity.getDate()));
            ps.setTime(3, Time.valueOf(entity.getBeginTime()));
            ps.setString(4, entity.getLocation());
            ps.setInt(5, entity.getAvailableSeats());
            ps.setInt(6, entity.getSoldSeats());
            ps.setInt(7, integer);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error ShowDB " + e);
        }
        logger.traceExit();
    }

    @Override
    public Show findOne(Integer integer) {
        logger.traceEntry("Finding show with id {} ", integer);
        Connection con = jdbcUtils.getConnection();
        Show show = null;
        try(PreparedStatement ps=con.prepareStatement("select * from artists_shows where showid=?")){
            ps.setInt(1,integer);
            ResultSet rs = ps.executeQuery();
            rs.next();

            int artistid = rs.getInt("artistid");
            String artistname = rs.getString("artistname");
            Artist artist = new Artist(artistid, artistname);

            LocalDate date = rs.getDate("date").toLocalDate();
            LocalTime begintime = rs.getTime("begintime").toLocalTime();
            String location = rs.getString("location");
            int availableseats = rs.getInt("availableseats");
            int soldseats = rs.getInt("soldseats");
            show = new Show(integer, artist, date, begintime, location, availableseats, soldseats);
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error ShowDB " + e);
        }
        logger.traceExit();
        return show;
    }

    @Override
    public Collection<Show> findAll() {
        logger.traceEntry("Finding all shows");
        Connection con = jdbcUtils.getConnection();
        Collection<Show> shows = new java.util.ArrayList<>();
        try(PreparedStatement ps=con.prepareStatement("select * from artists_shows")){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("showid");
                int artistid = rs.getInt("artistid");
                String artistname = rs.getString("artistname");
                Artist artist = new Artist(artistid, artistname);

                LocalDate date = rs.getDate("date").toLocalDate();
                LocalTime begintime = rs.getTime("begintime").toLocalTime();
                String location = rs.getString("location");
                int availableseats = rs.getInt("availableseats");
                int soldseats = rs.getInt("soldseats");
                Show show = new Show(id, artist, date, begintime, location, availableseats, soldseats);
                shows.add(show);
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error ShowDB " + e);
        }
        logger.traceExit();
        return shows;
    }

    @Override
    public Collection<Show> findByDay(LocalDate givenDate) {
        logger.traceEntry("Finding shows with date {} ", givenDate);
        Connection con = jdbcUtils.getConnection();
        Collection<Show> shows = new LinkedList<>();
        try(PreparedStatement ps=con.prepareStatement("select * from artists_shows where date=?")){
            ps.setDate(1, Date.valueOf(givenDate));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("showid");
                int artistid = rs.getInt("artistid");
                String artistname = rs.getString("artistname");
                Artist artist = new Artist(artistid, artistname);

                LocalDate date = rs.getDate("date").toLocalDate();
                LocalTime begintime = rs.getTime("begintime").toLocalTime();
                String location = rs.getString("location");
                int availableseats = rs.getInt("availableseats");
                int soldseats = rs.getInt("soldseats");
                Show show = new Show(id, artist, date, begintime, location, availableseats, soldseats);
                shows.add(show);
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error ArtistDB " + e);
        }
        logger.traceExit();
        return shows;
    }
}
