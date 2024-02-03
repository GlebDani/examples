package ru.danilenko.service;

import lombok.AllArgsConstructor;
import ru.danilenko.dao.AuditDAO;
import ru.danilenko.model.Audit;

import java.util.List;

@AllArgsConstructor
public class AuditService {

    AuditDAO auditDAO;



    public boolean addAction(String date, int userID, String desc){
        return auditDAO.addAction(date,userID, desc);
    }
    /**
     * method prints all audit action for chosen user
     * @param userId id of chosen user
     * @return List<Audit>
     */
    public List<Audit> getAllForUser(int userId) {
        return auditDAO.getAllForUser(userId);
    }
}
