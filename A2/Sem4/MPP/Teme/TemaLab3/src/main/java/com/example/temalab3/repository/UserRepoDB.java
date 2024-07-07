package com.example.temalab3.repository;

import com.example.temalab3.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Properties;

public class UserRepoDB implements UserRepository{
    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public UserRepoDB(String propFile) {
        Properties props=new Properties();
        try {
            FileReader fr = new FileReader(propFile);
            props.load(fr);
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }

        logger.info("Initializing CarsDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }


    @Override
    public int size() {
        logger.traceEntry("UserRepo Size");
        Connection con = dbUtils.getConnection();
        int size = 0;
        try(PreparedStatement ps=con.prepareStatement("select count(*) from users")){
            ResultSet rs = ps.executeQuery();
            rs.next();
            size = rs.getInt(1);
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error UserDB " + e);
        }
        logger.traceExit();
        return size;
    }

    @Override
    public void save(User entity) {
        logger.traceEntry("saving user {} ", entity);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement ps=con.prepareStatement("insert into users values (?,?)")){
            ps.setString(1,entity.getUsername());
            ps.setString(2,entity.getPassword());
            int result = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error UserDB " + e);
        }
        logger.traceExit();
    }

    @Override
    public void delete(Integer integer) {
        logger.traceEntry("deleting user with id {} ", integer);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement ps=con.prepareStatement("delete from users where id=?")){
            ps.setInt(1,integer);
            int result = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error UserDB " + e);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer integer, User entity) {
        logger.traceEntry("updating user {} ", entity);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement ps=con.prepareStatement("update users set username=?, password=? where id=?")){
            ps.setString(1,entity.getUsername());
            ps.setString(2,entity.getPassword());
            ps.setInt(3,entity.getId());
            int result = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error UserDB " + e);
        }
        logger.traceExit();
    }

    @Override
    public User findOne(Integer integer) {
        logger.traceEntry("Finding user with id {} ", integer);
        Connection con = dbUtils.getConnection();
        User user = null;
        try(PreparedStatement ps=con.prepareStatement("select * from users where id=?")){
            ps.setInt(1,integer);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Integer id = rs.getInt("id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            user = new User(id, username, password);
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error UserDB " + e);
        }
        logger.traceExit();
        return user;
    }

    @Override
    public Collection<User> findAll() {
        logger.traceEntry("Finding all users");
        Connection con = dbUtils.getConnection();
        Collection<User> users = new LinkedList<>();
        try(PreparedStatement ps=con.prepareStatement("select * from users")){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Integer id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                User user = new User(id, username, password);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error UserDB " + e);
        }
        logger.traceExit();
        return users;
    }

    @Override
    public User login(String username, String password) {
        logger.traceEntry("Finding user with username {} and password {}", username, password);
        Connection con = dbUtils.getConnection();
        User user = null;
        try(PreparedStatement ps=con.prepareStatement("select * from users where username=? and password=?")){
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Integer id = rs.getInt("id");
                user = new User(id, username, password);
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error UserDB " + e);
        }
        logger.traceExit();
        return user;
    }
}
