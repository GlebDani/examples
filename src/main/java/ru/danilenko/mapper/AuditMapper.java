package ru.danilenko.mapper;

import ru.danilenko.model.Audit;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * class for Audit {@link  Audit} mapping
 */
public class AuditMapper {
    /**
     * method returns list of obtained audit notes
     * @param resultSet after sql query
     * @return List<Audit>
     */
    public List<Audit> mapToAudit(ResultSet resultSet) {
        List<Audit> auditList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Audit audit = new Audit();
                audit.setActionId(resultSet.getInt("action_id"));
                audit.setDate(resultSet.getString("date"));
                audit.setUserId(resultSet.getInt("user_id"));
                audit.setOperation_desc(resultSet.getString("description"));
                auditList.add(audit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auditList;
    }
}
