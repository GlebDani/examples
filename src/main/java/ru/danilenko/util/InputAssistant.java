package ru.danilenko.util;

import lombok.AllArgsConstructor;
import ru.danilenko.model.User;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

@AllArgsConstructor
public class InputAssistant {

    Scanner scanner;


     public int listOfUsers(List<User> userList){
        System.out.println("Choose the User");
        for(int i=1; i<=userList.size(); i++){
            System.out.println(i + " -> "+ userList.get(i-1).getFirstName()+" "+userList.get(i-1).getFirstName()+" "+userList.get(i-1).getEmail() );
        }
        System.out.println("0 -> To quit");
        return readIntValue(0,userList.size());

    }

    public int readIntValue(int leftBoundary){
        Pattern pattern = Pattern.compile("^\\d+$");
        String readValue;
        do {
            readValue=scanner.nextLine();
        }while (!pattern.matcher(readValue).find() || Integer.parseInt(readValue)<leftBoundary);
        return Integer.parseInt(readValue);
    }
   public int readIntValue(int leftBoundary, int rightBoundary){
        Pattern pattern = Pattern.compile("^\\d+$");
        String readValue;
        do {
            readValue=scanner.nextLine();
        }while ((!pattern.matcher(readValue).find()) || (Integer.parseInt(readValue)<leftBoundary || Integer.parseInt(readValue)>rightBoundary));
        return Integer.parseInt(readValue);
    }

    public String nextLine(){
         return scanner.nextLine();
    }

    public String getInput(String parameter){
        String input;
        do{
            System.out.print(parameter+": ");
            input = scanner.nextLine();
        }while (input.equals(""));
        return  input;
    }

    public void pagination(){

    }


}
