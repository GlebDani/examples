package ru.danilenko.in;

import lombok.AllArgsConstructor;
import ru.danilenko.model.User;
import ru.danilenko.service.AssistanceService;
import ru.danilenko.service.CounterTypeService;


/**
 * class which help to navigate over the app
 * 1. to choose the available action
 * 2. to choose the available counter type
 */
@AllArgsConstructor
public class InputController {


    private AssistanceService assistanceService;
    private UserController userController;





    public void userActionDescription(){
        System.out.println("""
                1 -> To get current value.
                2 -> To insert new value.
                3 -> To get value for certain month.
                4 -> To get history.""");
    }

    public void moderatorActionDescription(){
        userActionDescription();
        System.out.println("5 -> To get all users data.");
    }
    public void adminActionDescription(){
        System.out.println("""
                1 -> To get all users data.
                2 -> To assign a moderator.
                3 -> To audit users action.
                4 -> To add a new counter.""");
    }


    public boolean userAction(User user){
        String operationType = String.valueOf(assistanceService.readIntValue(0,4));
        if(operationType.equals("0"))
            return false;
        return switch (operationType){
            case "1" -> userController.getCurrentValue(user);
            case "2" -> userController.insertValue(user);
            case "3" -> userController.getValueForCertainMonth(user);
            case "4"-> userController.getHistory(user);
            default -> false;
        };
    }
    public boolean moderatorAction( User user){
        String operationType = String.valueOf(assistanceService.readIntValue(0,5));
        if(operationType.equals("0"))
            return false;
        return switch (operationType){
            case "1" -> userController.getCurrentValue(user);
            case "2" -> userController.insertValue(user);
            case "3" -> userController.getValueForCertainMonth(user);
            case "4"-> userController.getHistory(user);
            case "5"-> userController.getAllUsersInfo(user);
            default -> false;
        };


    }
    public boolean adminAction( User user){
        String operationType = String.valueOf(assistanceService.readIntValue(0,4));
        if(operationType.equals("0"))
            return false;
        return switch (operationType){
            case "1" -> userController.getAllUsersInfo(user);
            case "2" -> userController.giveModeratorRights(user);
            case "3"-> userController.usersAudit();
            case "4" -> userController.addNewCounter(user);
            default -> false;
        };
    }






}
