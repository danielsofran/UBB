package com.example.temalab3.repository;

import com.example.temalab3.domain.Artist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Properties;

public class ArtistRepoDB implements ArtistRepository {
    private JdbcUtils jdbcUtils;
    private static final Logger logger= LogManager.getLogger();

    public ArtistRepoDB(String propFile) {
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
        logger.traceEntry("ArtistRepo Size");
        Connection con = jdbcUtils.getConnection();
        int size = 0;
        try(PreparedStatement ps=con.prepareStatement("select count(*) from artists")){
            ResultSet rs = ps.executeQuery();
            rs.next();
            size = rs.getInt(1);
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error ArtistDB " + e);
        }
        logger.traceExit();
        return size;
    }

    @Override
    public Artist findOne(Integer integer) {
        logger.traceEntry("Finding artist with id {} ", integer);
        Connection con = jdbcUtils.getConnection();
        Artist artist = null;
        try(PreparedStatement ps=con.prepareStatement("select * from artists where id=?")){
            ps.setInt(1,integer);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String name = rs.getString("name");
            artist = new Artist(integer, name);
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error ArtistDB " + e);
        }
        logger.traceExit();
        return artist;
    }

    @Override
    public Collection<Artist> findAll() {
        logger.traceEntry("Finding all artists");
        Connection con = jdbcUtils.getConnection();
        Collection<Artist> artists = new LinkedList<>();
        try(PreparedStatement ps=con.prepareStatement("select * from artists")){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Artist artist = new Artist(id, name);
                artists.add(artist);
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error ArtistDB " + e);
        }
        logger.traceExit();
        return artists;
    }

    @Override
    public void save(Artist entity) {
        logger.traceEntry("Saving artist {} ", entity);
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement ps=con.prepareStatement("insert into artists(name) values (?)")){
            ps.setString(1,entity.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error ArtistDB " + e);
        }
        logger.traceExit();
    }

    @Override
    public void delete(Integer integer) {
        logger.traceEntry("Deleting artist with id {} ", integer);
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement ps=con.prepareStatement("delete from artists where id=?")){
            ps.setInt(1,integer);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error ArtistDB " + e);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer integer, Artist entity) {
        logger.traceEntry("Updating artist with id {} ", integer);
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement ps=con.prepareStatement("update artists set name=? where id=?")){
            ps.setString(1,entity.getName());
            ps.setInt(2,integer);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error ArtistDB " + e);
        }
        logger.traceExit();
    }
}
