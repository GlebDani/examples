package ru.danilenko.dao;

import lombok.AllArgsConstructor;
import ru.danilenko.mapper.UserMapper;
import ru.danilenko.model.User;
import ru.danilenko.util.ConnectionToDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * DAO for user entity
 */
@AllArgsConstructor
public class UserDAO {

    private UserMapper userMapper;
    private ConnectionToDB connectionToDB;

    /**
     * method to create new user
     * @param email email
     * @param password password
     * @param firstName first name
     * @param  lastName last name
     * @param  role user's role
     * @return true after creation
     */
    public boolean create(String email, String password, String firstName, String lastName,String role) {
        try {
            PreparedStatement statement = connectionToDB.getConnection().
                    prepareStatement("insert into entity.user(email,password,first_name,last_name,role) values(?,?,?,?,?)");
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setString(5, role);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * method return a user from the list
     * @param email email
     * @return user based on email
     */
    public User findByEmail(String email) {
        User user;
        try {
            PreparedStatement statement = connectionToDB.getConnection().prepareStatement("select * from entity.user where email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = userMapper.mapToUser(resultSet);
            user = (users.isEmpty())?null:users.get(0);

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return user;
    }

    /**
     * method return a user from the list
     * @param userId user id
     * @return user based on id
     */
    public User findById(int userId) {
        User user;
        try {
            PreparedStatement statement = connectionToDB.getConnection().prepareStatement("select * from entity.user where user_id = ?");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = userMapper.mapToUser(resultSet);
            user = (users.isEmpty())?null:users.get(0);

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return user;
    }

    /**
     * method return the list of users
     * @return list
     */
    public List<User> findAllUser() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connectionToDB.getConnection().createStatement();
            String SQL = "select * from entity.user where role = 'USER' OR role = 'MODERATOR'";
            ResultSet resultSet = statement.executeQuery(SQL);
            users = userMapper.mapToUser(resultSet);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  users;

    }

    /**
     * method return the list of users
     * @return list
     */
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connectionToDB.getConnection().createStatement();
            String SQL = "select * from entity.user";
            ResultSet resultSet = statement.executeQuery(SQL);
            users = userMapper.mapToUser(resultSet);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  users;
    }

    /**
     * method gives a moderator right to a user with user id
     * @param userId user id
     */
    public void giveRight(int userId, String role) {
        try {
            PreparedStatement statement = connectionToDB.getConnection().prepareStatement("update entity.user set role = ? where user_id = ?");
            statement.setInt(2, userId);
            statement.setString(1, role);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}




