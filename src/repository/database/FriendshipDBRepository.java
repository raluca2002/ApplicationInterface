package repository.database;

import domain.Friendship;
import domain.User;
import domain.validators.Validator;
import repository.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

public class FriendshipDBRepository implements Repository<Long, Friendship> {

    private String url;
    private String userName;
    private String password;
    private Validator<Friendship> validator;

    public FriendshipDBRepository(String url, String userName, String password, Validator<Friendship> validator) {
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.validator = validator;
    }

    @Override
    public Friendship findOne(Long id) {

        return null;
    }

    @Override
    public Iterable<Friendship> findAll() {
        Set<Friendship> friendships = new HashSet<>();
        try(Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM friendship");
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                Long id = rs.getLong("id");
                Long id_u1 = rs.getLong("id_u1");
                Long id_u2 = rs.getLong("id_u2");
                //LocalDateTime friendsFrom = LocalDateTime.parse(rs.getString("date"));

                Friendship friendship = new Friendship(id_u1, id_u2);
                friendship.setId(id);
                friendships.add(friendship);
            }
            return friendships;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return friendships;
    }

    @Override
    public Friendship save(Friendship entity) {
        String sql = "INSERT INTO friendship(id_u1, id_u2, friendsFrom) VALUES (?,?,?);";
        try(Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1,entity.getId1());
            ps.setLong(2,entity.getId2());
            ps.setDate(3,Date.valueOf(entity.getFriendsFrom().toLocalDate()));
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Friendship delete(Long id) {
        String sql = "DELETE FROM friendship WHERE id=" + id + " RETURNING *";
        try(Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            if(rs.next())
                return getFriendship(rs);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Friendship update(Friendship entity) {
        String sql = "UPDATE friendship SET first_name = ?, last_name = ?, username = ?, password = ?  WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1,entity.getId1());
            ps.setLong(2,entity.getId2());
            ps.setDate(3,Date.valueOf(entity.getFriendsFrom().toLocalDate()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    private Friendship getFriendship(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        Long id_u1 = rs.getLong("id_u1");
        Long id_u2 = rs.getLong("id_u2");
        LocalDateTime date = rs.getDate("friends_from").toLocalDate().atStartOfDay();
        Friendship friendship = new Friendship(id_u1, id_u2);
        friendship.setId(id);
        return friendship;
    }
}

