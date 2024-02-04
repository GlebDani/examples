package ru.danilenko.mapper;

import ru.danilenko.model.Counter;
import ru.danilenko.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CounterMapper {

    public List<Counter> mapToCounter(ResultSet resultSet) {
        List<Counter> counters = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Counter counter = new Counter();
                counter.setCounterId(resultSet.getInt("counter_id"));
                counter.setUserId(resultSet.getInt("user_id"));
                counter.setDate(resultSet.getString("date"));
                counter.setCounterMeasure(resultSet.getInt("counterMeasure"));
                counter.setCounterTypeId(resultSet.getInt("counterType_id"));
                counters.add(counter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counters;
    }
}
