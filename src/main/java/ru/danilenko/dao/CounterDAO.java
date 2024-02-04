package ru.danilenko.dao;

import lombok.AllArgsConstructor;
import ru.danilenko.mapper.CounterMapper;
import ru.danilenko.model.Counter;
import ru.danilenko.model.User;
import ru.danilenko.util.ConnectionToDB;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@AllArgsConstructor
public class CounterDAO{

    private CounterMapper counterMapper;
    private ConnectionToDB connectionToDB;

    /**
     * method which create new note for
     * @param userId users id
     * @param date date of creating
     * @param counterValue inserted value
     * @param counterTypeId and type of counter
     */
    public boolean createNew(int userId,  String date, int counterValue,int counterTypeId) {
        try {
            PreparedStatement statement = connectionToDB.getConnection().
                    prepareStatement("insert into entity.counter(user_id,date,counterMeasure,counterType_id) values(?,?,?,?)");
            statement.setInt(1, userId);
            statement.setString(2, date);
            statement.setInt(3, counterValue);
            statement.setInt(4, counterTypeId);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * method which
     * @param userId users id
     * @param counterTypeId counter type
     * @return list of counters for certain User and certain type of counter
     */
    public List<Counter> getAllCertainType(int userId, int counterTypeId) {
        List<Counter> counters = new ArrayList<>();
        try {
            PreparedStatement statement = connectionToDB.getConnection().prepareStatement("select * from entity.counter where user_id = ? AND counterType_id = ?");
            statement.setInt(1, userId);
            statement.setInt(2, counterTypeId);
            ResultSet resultSet = statement.executeQuery();
            counters = counterMapper.mapToCounter(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counters;

    }




}
