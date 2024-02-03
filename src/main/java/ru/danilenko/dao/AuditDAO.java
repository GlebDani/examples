package ru.danilenko.dao;

import lombok.AllArgsConstructor;
import ru.danilenko.mapper.AuditMapper;
import ru.danilenko.model.Audit;
import ru.danilenko.model.Counter;
import ru.danilenko.model.User;
import ru.danilenko.util.ConnectionToDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DAO for audit entity
 */
@AllArgsConstructor
public class AuditDAO {

    AuditMapper auditMapper;

    /**
     * method to add a new audit action
     * @param date the date of the action
     * @param userId who performs action
     * @param operationDesc with operation description
     */
    public boolean addAction(String date, int userId, String operationDesc){

        try {
            PreparedStatement statement = ConnectionToDB.getConnection().
                    prepareStatement("insert into entity.audit(date,user_id,description) values(?,?,?)");
            statement.setString(1, date);
            statement.setInt(2, userId);
            statement.setString(3, operationDesc);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * method to get all data
     * @param userId id of chosen user
     * @return list of all action for chosen user
     */
    public List<Audit> getAllForUser(int userId) {
        List<Audit> auditList = new ArrayList<>();
        try {
            PreparedStatement statement = ConnectionToDB.getConnection().prepareStatement("select * from entity.audit where user_id = ?");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            auditList = auditMapper.mapToAudit(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auditList;

    }
}
