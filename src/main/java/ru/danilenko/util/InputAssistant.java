package ru.danilenko.util;

import lombok.AllArgsConstructor;
import ru.danilenko.model.User;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * class for getting User's input
 */
@AllArgsConstructor
public class InputAssistant {

    Scanner scanner;

    /**
     * method allow to choose the user to further action
     * @param userList list of user
     * @return chosen user number in the list
     */
     public int listOfUsers(List<User> userList){
        System.out.println("Choose the User");
        for(int i=1; i<=userList.size(); i++){
            System.out.println(i + " -> "+ userList.get(i-1).getFirstName()+" "+userList.get(i-1).getFirstName()+" "+userList.get(i-1).getEmail() );
        }
        System.out.println("0 -> To quit");
        return readIntValue(0,userList.size());

    }

    /**
     * method read user input and transfer in into integer
     * @param leftBoundary left limit
     * @return written int
     */
    public int readIntValue(int leftBoundary){
        Pattern pattern = Pattern.compile("^\\d+$");
        String readValue;
        do {
            readValue=scanner.nextLine();
        }while (!pattern.matcher(readValue).find() || Integer.parseInt(readValue)<leftBoundary);
        return Integer.parseInt(readValue);
    }

    /**
     * method read user input and transfer in into integer
     * @param leftBoundary left limit
     * @param rightBoundary right limit
     * @return written int
     */
   public int readIntValue(int leftBoundary, int rightBoundary){
        Pattern pattern = Pattern.compile("^\\d+$");
        String readValue;
        do {
            readValue=scanner.nextLine();
        }while ((!pattern.matcher(readValue).find()) || (Integer.parseInt(readValue)<leftBoundary || Integer.parseInt(readValue)>rightBoundary));
        return Integer.parseInt(readValue);
    }

    /**
     * implements reading the line
     * @return String
     */
    public String nextLine(){
         return scanner.nextLine();
    }

    /**
     * implement reading during authorisation
     * @param parameter the ouput parameter before reading the user's input
     * @return String user's input
     */
    public String getInput(String parameter){
        String input;
        do{
            System.out.print(parameter+": ");
            input = scanner.nextLine();
        }while (input.equals(""));
        return  input;
    }

    /**
     * pagination of output
     */
    public void pagination(){

    }


}
