package ru.danilenko.service;

import lombok.AllArgsConstructor;
import ru.danilenko.dao.UserDAO;
import ru.danilenko.model.User;

import java.util.List;

@AllArgsConstructor
public class UserService {

    UserDAO userDAO;



    public List<User> findAllUser(){
        return userDAO.findAllUser();
    }
    public List<User> findAll(){
        return userDAO.findAll();
    }

    /**
     * method gives moderator rights
     * @param userId id of chosen user
     */
    public void giveRight(int userId) {
        userDAO.giveRight(userId);
    }

    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public boolean create(String email, String password, String firstName, String lastName) {
        return userDAO.create(email,password,firstName,lastName);
    }
}
