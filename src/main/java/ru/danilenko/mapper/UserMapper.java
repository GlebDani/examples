package ru.danilenko.mapper;

import ru.danilenko.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public List<User> mapToUser(ResultSet resultSet){
        List<User> users = new ArrayList<>();
        try{
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setRole(resultSet.getString("role"));
                users.add(user);
        }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;


    }
}
