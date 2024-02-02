package ru.danilenko.dao;

import ru.danilenko.Enum.Roles;
import ru.danilenko.model.User;

import java.util.ArrayList;
import java.util.List;
/**
 * DAO for user entity
 */
public class UserDAO {
    /**
     * list to hold users
     */
    private List<User> usersList = new ArrayList<>();

    {
        usersList.add(new User(1,"Admin@mail.ru","admin","Admin", "Admin", Roles.ROLE_ADMIN));
        usersList.add(new User(2,"User2@mail.ru","user2","User2","User2", Roles.ROLE_USER));
        usersList.add(new User(3,"User3@mail.ru","user3","User3","User3", Roles.ROLE_USER));
        usersList.add(new User(4,"User4@mail.ru","user4","User4","User4", Roles.ROLE_USER));
    }
    /**
     * static counter to generate user_id
     */
    private int id = usersList.size() +1;

    /**
     * method to create new user
     * @param email email
     * @param password password
     * @param firstName first name
     * @param  lastName last name
     * @return true after creation
     */
    public boolean create(String email, String password, String firstName, String lastName) {
            usersList.add(new User(id++, email, password, firstName,lastName, Roles.ROLE_USER));
            return true;

    }
    /**
     * method return a user from the list
     * @param email email
     * @return user based on email
     */
    public User findByEmail(String email) {
        return usersList.stream().filter(person -> person.getEmail().equals(email)).findAny().orElse(null);
    }

    /**
     * method return the list of users
     * @return list
     */
    public List<User> findAllUser() {
        return usersList.stream().filter(u->u.getRole()!=Roles.ROLE_ADMIN).toList();
    }

    /**
     * method return the list of users
     * @return list
     */
    public List<User> findAll() {
        return usersList;
    }

    /**
     * method gives a moderator right to a user with user id
     * @param user_id user id
     */
    public void giveRight(int user_id) {
        usersList.get(user_id-1).setRole(Roles.ROLE_MODERATOR);
    }
}




