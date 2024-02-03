package ru.danilenko;

import ru.danilenko.dao.*;
import ru.danilenko.in.Authentication;
import ru.danilenko.mapper.AuditMapper;
import ru.danilenko.mapper.CounterMapper;
import ru.danilenko.mapper.CounterTypeMapper;
import ru.danilenko.mapper.UserMapper;
import ru.danilenko.util.InputAssistant;
import ru.danilenko.in.InputController;
import ru.danilenko.in.ActionController;
import ru.danilenko.model.User;
import ru.danilenko.service.*;

import java.util.Scanner;

/**
 * class to compile the app
 */

public class Compilation {


    private AuditDAO auditDAO = new AuditDAO(new AuditMapper());
    private CounterDAO counterDAO = new CounterDAO(new CounterMapper());
    private CounterTypeDAO counterTypeDAO = new CounterTypeDAO(new CounterTypeMapper());
    private UserDAO userDAO = new UserDAO(new UserMapper());
    private Scanner scanner = new Scanner(System.in);
    private InputAssistant inputAssistant = new InputAssistant(scanner);

    private AuditService auditService = new AuditService(auditDAO);
    private CounterService counterService= new CounterService(counterDAO);
    private CounterTypeService counterTypeService = new CounterTypeService(counterTypeDAO);
    private UserService userService= new UserService(userDAO);


    private Authentication authentication = new Authentication(userService, inputAssistant);
    private ActionController actionController = new ActionController(auditService,counterService,counterTypeService,userService, inputAssistant);
    private InputController inputController = new InputController(inputAssistant, actionController);

    /**
     * Method which help to navigate over the app
     * @return boolean which shows if we would like to continue or to log out
     * @param user is used to demonstrate possible action
     */

    public boolean actionOnSite(User user){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        return userMenu(user);


    }

    /**
     * method starts the application and keep us in the system until we want to close the app
     */
    public void appStart(){
        while (true){
            User user = authentication.startUp();
            boolean inProgress = true;
            while (inProgress) {
                inProgress = actionOnSite(user);
            }
        }
    }

    public boolean userMenu(User user){
         switch (user.getRole()){
            case "USER"-> {
                inputController.userActionDescription();
                System.out.println("0 -> To quit.");
                return inputController.userAction(user);

            }
            case "MODERATOR" -> {
                inputController.moderatorActionDescription();
                System.out.println("0 -> To quit.");
                return inputController.moderatorAction(user);

            }
            case "ADMIN" -> {
                inputController.adminActionDescription();
                System.out.println("0 -> To quit.");
                return inputController.adminAction(user);

            }
        }
        return false;
    }





}
