package ru.danilenko.dao;

import ru.danilenko.model.Counter;
import ru.danilenko.model.User;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DAO for counter entity
 */
public class CounterDAO{

    /**
     * list to hold counters
     */
    private List<Counter> countersList = new ArrayList<>();


    {
        countersList.add(new Counter(1,2,DateTimeFormatter.ofPattern("MM-yyyy").format(LocalDate.now().minus(4, ChronoUnit.MONTHS)),100,1));
        countersList.add(new Counter(2,2,DateTimeFormatter.ofPattern("MM-yyyy").format(LocalDate.now().minus(3, ChronoUnit.MONTHS)),220,1));

        countersList.add(new Counter(3,2,DateTimeFormatter.ofPattern("MM-yyyy").format(LocalDate.now().minus(2, ChronoUnit.MONTHS)),300,1));
        countersList.add(new Counter(4,2,DateTimeFormatter.ofPattern("MM-yyyy").format(LocalDate.now().minus(2, ChronoUnit.MONTHS)),110,2));


        countersList.add(new Counter(5,2,DateTimeFormatter.ofPattern("MM-yyyy").format(LocalDate.now().minus(1, ChronoUnit.MONTHS)),450,1));
        countersList.add(new Counter(6,2,DateTimeFormatter.ofPattern("MM-yyyy").format(LocalDate.now().minus(1, ChronoUnit.MONTHS)),200,2));

        countersList.add(new Counter(7,3,DateTimeFormatter.ofPattern("MM-yyyy").format(LocalDate.now().minus(1, ChronoUnit.MONTHS)),80,1));
        countersList.add(new Counter(8,3,DateTimeFormatter.ofPattern("MM-yyyy").format(LocalDate.now().minus(2, ChronoUnit.MONTHS)),150,3));
    } /**
     * static counter to generate audit_id
     */

    private int counter_id = countersList.size()+1;

    /**
     * method which
     * @param userId users id
     * @return list of counters for certain User with
     */
    public List<Counter> findByUserId(int userId){
        return countersList.stream().filter(c->c.getUserId()==userId).toList();

    }

    /**
     * method which create new note for
     * @param userId users id
     * @param date date of creating
     * @param counterValue inserted value
     * @param counterTypeId and type of counter
     */
    public void createNew(int userId,  String date, int counterValue,int counterTypeId) {
        countersList.add(new Counter(counter_id++,userId, date,counterValue,counterTypeId));

    }

    /**
     * method which
     * @param userId users id
     * @param counterTypeId counter type
     * @return list of counters for certain User and certain type of counter
     */
    public List<Counter> getAllCertainType(int userId, int counterTypeId) {
        return countersList.stream().filter(c->c.getUserId()==userId).filter(c->c.getCounterTypeId()==counterTypeId).toList();
    }




}
