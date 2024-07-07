package com.example.temalab3.repository;

import com.example.temalab3.domain.Artist;
import com.example.temalab3.domain.Show;
import com.example.temalab3.domain.Ticket;
import com.example.temalab3.exceptions.RepoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Properties;

public class TicketRepoDB implements TicketRepository {
    private JdbcUtils jdbcUtils;
    private static final Logger logger= LogManager.getLogger();

    public TicketRepoDB(String propFile) {
        Properties props=new Properties();
        try {
            FileReader fr = new FileReader(propFile);
            props.load(fr);
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }

        jdbcUtils = new JdbcUtils(props);
    }

    @Override
    public int size() {
        logger.traceEntry("TicketRepo Size");
        Connection con = jdbcUtils.getConnection();
        int size = 0;
        try(PreparedStatement ps=con.prepareStatement("select count(*) from tickets")){
            ResultSet rs = ps.executeQuery();
            rs.next();
            size = rs.getInt(1);
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error TicketDB " + e);
        }
        logger.traceExit();
        return size;
    }

    @Override
    public Ticket findOne(Integer integer) {
        logger.traceEntry("Finding ticket with id {} ", integer);
        Connection con = jdbcUtils.getConnection();
        Ticket ticket = null;
        try(PreparedStatement ps=con.prepareStatement("select * from tickets_shows where id=?")){
            ps.setInt(1,integer);
            ResultSet rs = ps.executeQuery();
            rs.next();

            int artist_id = rs.getInt("artistid");
            String artist_name = rs.getString("artistname");
            Artist artist = new Artist(artist_id, artist_name);

            int show_id = rs.getInt("show_id");
            LocalDate date = rs.getDate("date").toLocalDate();
            LocalTime begintime = rs.getTime("begintime").toLocalTime();
            String location = rs.getString("location");
            int availableseats = rs.getInt("availableseats");
            int soldseats = rs.getInt("soldseats");
            Show show = new Show(show_id, artist, date, begintime, location, availableseats, soldseats);

            String costumername = rs.getString("costumername");
            int seats = rs.getInt("seats");
            ticket = new Ticket(integer, show, costumername, seats);
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error TicketDB " + e);
        }
        logger.traceExit();
        return ticket;
    }

    @Override
    public Collection<Ticket> findAll() {
        logger.traceEntry("Finding all tickets");
        Connection con = jdbcUtils.getConnection();
        Collection<Ticket> tickets = new LinkedList<>();
        try(PreparedStatement ps=con.prepareStatement("select * from tickets_shows")){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");

                int artist_id = rs.getInt("artistid");
                String artist_name = rs.getString("artistname");
                Artist artist = new Artist(artist_id, artist_name);

                int show_id = rs.getInt("show_id");
                LocalDate date = rs.getDate("date").toLocalDate();
                LocalTime begintime = rs.getTime("begintime").toLocalTime();
                String location = rs.getString("location");
                int availableseats = rs.getInt("availableseats");
                int soldseats = rs.getInt("soldseats");
                Show show = new Show(show_id, artist, date, begintime, location, availableseats, soldseats);

                String costumername = rs.getString("costumername");
                int seats = rs.getInt("seats");
                Ticket ticket = new Ticket(id, show, costumername, seats);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error TicketDB " + e);
        }
        logger.traceExit();
        return tickets;
    }

    @Override
    public void save(Ticket entity) {
        logger.traceEntry("Saving ticket {} ", entity);
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement ps=con.prepareStatement("insert into tickets(show_id, costumername, seats) values (?,?,?)")){
            ps.setInt(1,entity.getShow().getId());
            ps.setString(2,entity.getCostumerName());
            ps.setInt(3,entity.getSeats());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error TicketDB " + e);
            throw new RepoException("Ticket not added!");
        }
        logger.traceExit();
    }

    @Override
    public void delete(Integer integer) {
        logger.traceEntry("Deleting ticket with id {} ", integer);
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement ps=con.prepareStatement("delete from tickets where id=?")){
            ps.setInt(1,integer);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error TicketDB " + e);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer integer, Ticket entity) {
        logger.traceEntry("Updating ticket {} ", entity);
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement ps=con.prepareStatement("update tickets set show_id=?, costumername=?, seats=? where id=?")){
            ps.setInt(1,entity.getShow().getId());
            ps.setString(2,entity.getCostumerName());
            ps.setInt(3,entity.getSeats());
            ps.setInt(4,integer);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error TicketDB " + e);
        }
        logger.traceExit();
    }
}
