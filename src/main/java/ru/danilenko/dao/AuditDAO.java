package ru.danilenko.dao;

import ru.danilenko.model.Audit;
import ru.danilenko.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DAO for audit entity
 */
public class AuditDAO {
    /**
     * static counter to generate audit_id
     */
    private static int counter = 1;

    /**
     * list to hold audit actions
     */
    private List<Audit> auditActionList = new ArrayList<>();


    /**
     * method to add a new audit action
     * @param date the date of the action
     * @param userId who performs action
     * @param operationDesc with operation description
     */
    public void addAction(String date, int userId, String operationDesc){
        auditActionList.add(new Audit(counter++, date, userId, operationDesc));
    }

    /**
     * method to get all data
     * @param userId id of chosen user
     * @return list of all action for chosen user
     */
    public List<Audit> getAllForUser(int userId) {
        return auditActionList.stream().filter(a->a.getUserId() == userId).toList();
    }
}
