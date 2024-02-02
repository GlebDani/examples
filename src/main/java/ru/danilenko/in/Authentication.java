package ru.danilenko.in;


import ru.danilenko.dao.UserDAO;
import ru.danilenko.model.User;
import ru.danilenko.service.AssistanceService;
import ru.danilenko.service.UserService;

import java.util.Scanner;
/**
 * class to perform authentication
 */
public class Authentication {

    private AssistanceService assistanceService;
    private UserService userService;
    /**
     * email variable
     */
    private String email;
    /**
     * password variable
     */
    private  String password;
    /**
     * first name variable
     */
    private String firstName;
    /**
     * last name variable
     */
    private String lastName;

    public Authentication(UserService userService, AssistanceService assistanceService) {

        this.userService = userService;
        this.assistanceService = assistanceService;

    }
    /**
     * method to sing up
     * @return true if account created
     */
    public boolean singUp(){
        System.out.print("Create your account info\n");
        getAuthInfo();
        if(userService.findByEmail(email)==null) {
            getSingUpInfo();
            return userService.create(email,password,firstName,lastName);

        }
        else{
            System.out.println("Name already exists");
            return false;
        }
    }
    /**
     * method to log in
     * @return User which logs
     */
    public User logIn(){
        System.out.print("Enter your account info\n");
        getAuthInfo();
        if(checkValidity(email,password))
           return userService.findByEmail(email);
        else {
            System.out.println("Try again");
            return null;
        }
    }
    /**
     * method to get email and password
     */
    private void getAuthInfo(){

        email = assistanceService.getInput("To quit type \"/\" \nEmail");
        if(email.equals("/")) {
            startUp();
        }
        password = assistanceService.getInput("Password");

    }
    /**
     * method to get first and last names
     */
    private void getSingUpInfo(){
        firstName = assistanceService.getInput("First name");
        lastName = assistanceService.getInput("Last name");
    }

    /**
     * method to start authentication process
     * @return logged user
     */
    public User startUp(){
        System.out.print("Welcome.\n To log in type \"L\"\n To sing up type \"S\" \n To quit \"/\"\n");
        String action = assistanceService.nextLine().toLowerCase();
        User user;
        if(action.equals("l")) {
            do {
                user = logIn();
            } while (user == null);
            return user;
        }
        else if(action.equals("s")){
            boolean sing;
            do{
                sing = singUp();
            }while (!sing);
            do {
                user = logIn();
            } while (user == null);
            return user;
            }
        else if(action.equals("/")) {
            System.exit(0);
            return null;
        }
        else
            return startUp();

    }

    /**
     * method to get users info
     * @param parameter to transfer the parameter name
     * @return siring
     */


    /**
     * method to check inserted email and password
     * @param email user's email
     * @param password user's password
     * @return true if password and email are right
     */
    public boolean checkValidity(String email, String password) {
        return userService.findByEmail(email) != null && userService.findByEmail(email).getPassword().equals(password);
    }

}
