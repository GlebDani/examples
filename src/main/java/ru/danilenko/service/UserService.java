package ru.danilenko.service;

import lombok.AllArgsConstructor;
import ru.danilenko.dao.UserDAO;
import ru.danilenko.model.User;

import java.util.List;

@AllArgsConstructor
public class UserService {

    UserDAO userDAO;


    /**
     * method returns list of users that are not ADMIN
     * @return List<User>
     */
    public List<User> findAllUser(){
        return userDAO.findAllUser();
    }
    /**
     * method returns list of all users
     * @return List<User>
     */
    public List<User> findAll(){
        return userDAO.findAll();
    }

    /**
     * method gives moderator rights
     * @param userId id of chosen user
     */
    public void giveRight(int userId) {
        userDAO.giveRight(userId,"MODERATOR");
    }

    /**
     * method returns the user with specified user's email
     * @param email email
     * @return User
     */
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    /**
     * method create a new user
     * @param email email
     * @param password password
     * @param firstName first name
     * @param lastName last name
     * @param role role
     * @return boolean true if created
     */
    public boolean create(String email, String password, String firstName, String lastName, String role) {
        return userDAO.create(email,password,firstName,lastName, role);
    }
}
