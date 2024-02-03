package ru.danilenko.in;

import lombok.AllArgsConstructor;
import ru.danilenko.model.User;
import ru.danilenko.util.InputAssistant;


/**
 * class which help to navigate over the app
 * 1. to choose the available action
 * 2. to choose the available counter type
 */
@AllArgsConstructor
public class InputController {


    private InputAssistant inputAssistant;
    private ActionController actionController;





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
        String operationType = String.valueOf(inputAssistant.readIntValue(0,4));
        if(operationType.equals("0"))
            return false;
        return switch (operationType){
            case "1" -> actionController.getCurrentValue(user);
            case "2" -> actionController.insertValue(user);
            case "3" -> actionController.getValueForCertainMonth(user);
            case "4"-> actionController.getHistory(user);
            default -> false;
        };
    }
    public boolean moderatorAction( User user){
        String operationType = String.valueOf(inputAssistant.readIntValue(0,5));
        if(operationType.equals("0"))
            return false;
        return switch (operationType){
            case "1" -> actionController.getCurrentValue(user);
            case "2" -> actionController.insertValue(user);
            case "3" -> actionController.getValueForCertainMonth(user);
            case "4"-> actionController.getHistory(user);
            case "5"-> actionController.getAllUsersInfo(user);
            default -> false;
        };


    }
    public boolean adminAction( User user){
        String operationType = String.valueOf(inputAssistant.readIntValue(0,4));
        if(operationType.equals("0"))
            return false;
        return switch (operationType){
            case "1" -> actionController.getAllUsersInfo(user);
            case "2" -> actionController.giveModeratorRights(user);
            case "3"-> actionController.usersAudit();
            case "4" -> actionController.addNewCounter(user);
            default -> false;
        };
    }






}
