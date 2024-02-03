package ru.danilenko.in;

import lombok.AllArgsConstructor;
import ru.danilenko.model.Audit;
import ru.danilenko.model.Counter;
import ru.danilenko.model.CounterType;
import ru.danilenko.model.User;
import ru.danilenko.service.*;
import ru.danilenko.util.InputAssistant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * User service is a layer to connect DAO's and user actions
 */
@AllArgsConstructor
public class ActionController {

    private AuditService auditService;
    private CounterService counterService;
    private CounterTypeService counterTypeService;
    private UserService userService;
    private InputAssistant inputAssistant;





    /**
     * method prints current value if exists
     * @param user logged user
     * @return true if value exists and false if not
     */
    public boolean getCurrentValue(User user){
        int counterTypeId = getCounterType();
        if(counterTypeId==0)
            return true;
        Optional<Counter> counter = counterService.getCurrentValue(user.getId(), counterTypeId);
        if(counter.isPresent()) {
            System.out.println(counter.get().getCounterMeasure());
            auditService.addAction(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm").format(LocalDateTime.now()),user.getId(),  "get current value for "+counterTypeService.findById(counterTypeId).getDescription());
        }
        else {
            System.out.println("There is no value");
        }
        return true;
    }

    /**
     * method inserts value if not exists
     * @param user logged user
     * @return true if value inserted and false if not
     */
    public boolean insertValue(User user){
        int counterTypeId = getCounterType();
        if(counterTypeId==0)
            return true;
        if(ifExist(user.getId(),counterTypeId)) {
            System.out.println("You have already added value");
        }
        else {
            System.out.println("Type the value");
            int value = inputAssistant.readIntValue(0);
            counterService.insertValue(user.getId(), DateTimeFormatter.ofPattern("MM-yyyy").format(LocalDate.now()), value, counterTypeId);
            auditService.addAction(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm").format(LocalDateTime.now()),user.getId(), "get insert a new value for "+counterTypeService.findById(counterTypeId).getDescription());
        }
        return true;
    }
    /**
     * method prints value for certain month if exists
     * @param user logged user
     * @return true if value is shown and false if not
     */
    public boolean getValueForCertainMonth(User user){
        int counterTypeId = getCounterType();
        if(counterTypeId==0)
            return true;
        System.out.println("Write the month number");
        int monthNumber = inputAssistant.readIntValue(1,12);
        String date;
        if (monthNumber >LocalDate.now().getMonthValue())
            date =  DateTimeFormatter.ofPattern("MM-yyyy").format(LocalDate.of(LocalDate.now().getYear()-1, monthNumber ,1));
        else
            date = DateTimeFormatter.ofPattern("MM-yyyy").format(LocalDate.of(LocalDate.now().getYear(), monthNumber ,1));
        Optional<Counter> counter = counterService.getValueForCertainMonth(user.getId(),counterTypeId, date);
        if(counter.isEmpty()) {
            System.out.println("You do not have any");
        }
        else {
            System.out.println("The value is "+ counter.get().getCounterMeasure());
            auditService.addAction(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm").format(LocalDateTime.now()),user.getId(), "get value for "+counterTypeService.findById(counterTypeId).getDescription() +" on "+date);
        }
        return true;
    }

    /**
     * method prints current value if exists
     * @param user logged user
     * @return true when history shown
     */
    public boolean getHistory(User user){
        int counterTypeId = getCounterType();
        if(counterTypeId==0)
            return true;
        List<Counter> counterList = counterService.getAllCertainType(user.getId(),counterTypeId);
        for(Counter counter : counterList){
            System.out.println("On " + counter.getDate() + " type is "+counterTypeService.findById(counter.getCounterTypeId()).getDescription()+ " value " + counter.getCounterMeasure());
        }
        auditService.addAction(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm").format(LocalDateTime.now()), user.getId(), "get history for "+counterTypeService.findById(counterTypeId).getDescription());
        return true;
    }

    /**
     * method prints all counters for certain user
     * @param user logged user
     * @return true if person chosen and false if user exits
     */
    public boolean getAllUsersInfo(User user){

        List<User> userList = userService.findAllUser();
        int value = inputAssistant.listOfUsers(userList);
        if (value==0)
            return true; // will return to main menu with actions
        int counterTypeId = getCounterType();
        if(counterTypeId==0)
            return true;
        System.out.println("History for user "+userList.get(value-1).getFirstName() +" "+userList.get(value-1).getLastName()+ " "+userList.get(value-1).getEmail()+" for "+counterTypeService.findById(counterTypeId).getDescription());
        List<Counter> counterList = counterService.getAllCertainType(userList.get(value-1).getId(), counterTypeId);
        auditService.addAction(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm").format(LocalDateTime.now()),user.getId(), "get all users history");
        for(Counter counter : counterList){
            System.out.println("On " + counter.getDate() + " type is "+counterTypeService.findById(counter.getCounterTypeId()).getDescription()+ " value " + counter.getCounterMeasure());
        }
        return true;
    }

    /**
     * method gives moderator rights
     * @param user logged user
     * @return true if person chosen and false if user exits
     */
    public boolean giveModeratorRights(User user){
        List<User> userList = userService.findAllUser();
        int value = inputAssistant.listOfUsers(userList);
        if (value==0)
            return true;// will return to main menu with actions
        userService.giveRight(userList.get(value-1).getId());
        System.out.println("Rights given");
        auditService.addAction(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm").format(LocalDateTime.now()),user.getId(), "gave moderator right to user with userId "+ userList.get(value-1).getId());
        return true;


    }
    /**
     * method prints all audit action
     * @return true then audin action shown
    */
    public boolean usersAudit(){
        List<User> userList = userService.findAll();
        int value = inputAssistant.listOfUsers(userList);
        if (value==0)
            return true;// will return to main menu with actions
        List<Audit> auditList = auditService.getAllForUser(value);
        User user = userList.get(value-1);
        for (Audit audit:auditList){
            System.out.println("User "+user.getFirstName() + " "+ user.getLastName() + " " + audit.getOperation_desc() + " at "+audit.getDate());
        }
        return true;
    }

    /**
     * method add new counter type
     * @param user logged user
     * @return true if new counter type added and false if user exits
    */
    public  boolean addNewCounter(User user){
        System.out.println("Write the type of counter");
        String desc;
        System.out.println("\"/\" to quit");
        do {
            desc= inputAssistant.nextLine();
        }while (desc.equals(""));
        if (desc.equals("/"))
            return true;// will return to main menu with actions
        if(counterTypeService.ifExist(desc))
            System.out.println("Counter is already existed");
        else {
            counterTypeService.addNewCounter(desc);
            System.out.println("New counter of " + desc + " created");
            auditService.addAction(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm").format(LocalDateTime.now()), user.getId(), "add new counter type " + desc);
        }
        return true;
    }

    /**
     * method checks if counter is exists for user and counter type
     * @param user_id user id
     * @param counterType_id counterType id
     * @return true counter value exist and false if not
     */
    public boolean ifExist(int user_id, int counterType_id){
        String dateString = DateTimeFormatter.ofPattern("MM-yyyy").format(LocalDate.now());
        if(counterService.getCurrentValue(user_id, counterType_id).isPresent()) {
            return dateString.equals(counterService.getCurrentValue(user_id, counterType_id).get().getDate());
        }
        else
            return false;
    }

    /**
     * method return integer of chosen user
     * @param userList list of users available to choose
     * @return integer with chosen user id
     */



    /**
     * method which returns list of possible counterType id
     * @return int of counterType id
     */
    public int getCounterType(){
        System.out.println("Which type would you like to choose");
        List<CounterType> listOfCounter = new ArrayList<>(counterTypeService.getAll());
        List<Integer> allowedType = new ArrayList<>();
        for (CounterType counterType : listOfCounter){
            allowedType.add(counterType.getCounterTypeId());
            System.out.println(counterType.getCounterTypeId() +" -> "+counterType.getDescription());
        }
        System.out.println("0 -> To quit");
        return inputAssistant.readIntValue(0,allowedType.size());
    }






}
