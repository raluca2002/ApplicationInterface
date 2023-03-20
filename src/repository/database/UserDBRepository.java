package repository.database;

import domain.User;
import domain.validators.Validator;
import repository.Repository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class UserDBRepository implements Repository<Long, User> {

    private String url;
    private String userName;
    private String password;
    private Validator<User> validator;

    public UserDBRepository(String url, String userName, String password, Validator<User> validator) {
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.validator = validator;
    }

    @Override
    public User findOne(Long id) {
        return null;
    }

    @Override
    public Iterable<User> findAll() {
        Set<User> users = new HashSet<>();
        try(Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                Long id = rs.getLong("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String username = rs.getString("username");
                String password = rs.getString("password");

                User user = new User(firstName,lastName,username,password);
                user.setId(id);
                users.add(user);
            }
            return users;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User save(User entity) {
        String sql = "INSERT INTO users(first_name, last_name, username, password) VALUES (?,?,?,?);";
        try(Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,entity.getFirstName());
            ps.setString(2,entity.getLastName());
            ps.setString(3,entity.getUsername());
            ps.setString(4,entity.getPassword());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public User delete(Long id) {
        String sql = "DELETE FROM users WHERE id=" + id + " RETURNING *";
        try(Connection connection = DriverManager.getConnection(url, userName, password);
         PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            if(rs.next())
                return getUser(rs);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User update(User entity) {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, username = ?, password = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1,entity.getId());
            ps.setString(2,entity.getFirstName());
            ps.setString(3,entity.getLastName());
            ps.setString(4,entity.getUsername());
            ps.setString(5,entity.getPassword());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    private User getUser(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String username = rs.getString("username");
        String password = rs.getString("password");
        User user = new User(firstName, lastName,username,password);
        user.setId(id);
        return user;
    }
}
