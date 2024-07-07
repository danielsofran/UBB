package repo;

import domain.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class UserRepoDB implements Repository<Long, User>{
    private String url;
    private String username;
    private String password;

    public UserRepoDB(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public User findOne(Long aLong) {
        return null;
    }

    @Override
    public Iterable<User> findAll() {
        Set<User> users = new HashSet<>();
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                User user = new User(id, name);
                users.add(user);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User save(User entity) {
        String sql = "INSERT INTO users (name) VALUES (?)";
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getName());
            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public User delete(Long aLong) {
        return null;
    }

    @Override
    public User update(User entity) {
        return null;
    }
}
