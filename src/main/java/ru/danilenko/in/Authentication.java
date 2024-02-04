package ru.danilenko.in;


import ru.danilenko.model.User;
import ru.danilenko.service.UserService;
import ru.danilenko.util.InputAssistant;

/**
 * class to perform authentication
 */
public class Authentication {

    private InputAssistant inputAssistant;
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

    public Authentication(UserService userService, InputAssistant inputAssistant) {

        this.userService = userService;
        this.inputAssistant = inputAssistant;

    }
    /**
     * method to sing up
     * @return true if account created
     */
    private boolean singUp(){
        System.out.print("Create your account info\n");
        getAuthInfo();
        if(userService.findByEmail(email)==null) {
            getSingUpInfo();
            return userService.create(email,password,firstName,lastName, "USER");
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
    private User logIn(){
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

        email = inputAssistant.getInput("To quit type \"/\" \nEmail");
        if(email.equals("/")) {
            startUp();
        }
        password = inputAssistant.getInput("Password");

    }
    /**
     * method to get first and last names
     */
    private void getSingUpInfo(){
        firstName = inputAssistant.getInput("First name");
        lastName = inputAssistant.getInput("Last name");
    }

    /**
     * method to start authentication process
     * @return logged user
     */
    public User startUp(){
        System.out.print("Welcome.\n To log in type \"L\"\n To sing up type \"S\" \n To quit \"/\"\n");
        String action = inputAssistant.nextLine().toLowerCase();
        User user;
        switch (action) {
            case "l" -> {
                do {
                    user = logIn();
                } while (user == null);
                return user;
            }
            case "s" -> {
                boolean sing;
                do {
                    sing = singUp();
                } while (!sing);
                do {
                    user = logIn();
                } while (user == null);
                return user;
            }
            case "/" -> {
                System.exit(0);
                return null;
            }
            default -> {
                return startUp();
            }
        }

    }


    /**
     * method to check inserted email and password
     * @param email user's email
     * @param password user's password
     * @return true if password and email are right
     */
    private boolean checkValidity(String email, String password) {
        return userService.findByEmail(email) != null && userService.findByEmail(email).getPassword().equals(password);
    }

}
