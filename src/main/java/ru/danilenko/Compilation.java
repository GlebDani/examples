package ru.danilenko;

import ru.danilenko.dao.*;
import ru.danilenko.in.Authentication;
import ru.danilenko.in.InputController;
import ru.danilenko.in.UserController;
import ru.danilenko.model.User;
import ru.danilenko.service.*;

import java.util.Scanner;

/**
 * class to compile the app
 */

public class Compilation {


    private AuditDAO auditDAO = new AuditDAO();
    private CounterDAO counterDAO = new CounterDAO();
    private CounterTypeDAO counterTypeDAO = new CounterTypeDAO();
    private UserDAO userDAO = new UserDAO();
    private Scanner scanner = new Scanner(System.in);
    private AssistanceService assistanceService = new AssistanceService(scanner);

    private AuditService auditService = new AuditService(auditDAO);
    private CounterService counterService= new CounterService(counterDAO);
    private CounterTypeService counterTypeService = new CounterTypeService(counterTypeDAO);
    private UserService userService= new UserService(userDAO);


    private Authentication authentication = new Authentication(userService,assistanceService);
    private UserController userController = new UserController(auditService,counterService,counterTypeService,userService,assistanceService );
    private InputController inputController = new InputController(assistanceService ,userController);

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
            case ROLE_USER -> {
                inputController.userActionDescription();
                System.out.println("0 -> To quit.");
                return inputController.userAction(user);

            }
            case ROLE_MODERATOR -> {
                inputController.moderatorActionDescription();
                System.out.println("0 -> To quit.");
                return inputController.moderatorAction(user);

            }
            case ROLE_ADMIN -> {
                inputController.adminActionDescription();
                System.out.println("0 -> To quit.");
                return inputController.adminAction(user);

            }
        };

        return false;
    }





}
