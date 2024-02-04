package ru.danilenko.mapper;

import ru.danilenko.model.CounterType;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CounterTypeMapper {

    public List<CounterType> mapToCounterType(ResultSet resultSet) {
        List<CounterType> counterTypeList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                CounterType counterType = new CounterType();
                counterType.setCounterTypeId(resultSet.getInt("countertype_id"));
                counterType.setDescription(resultSet.getString("countertype_id"));
                counterTypeList.add(counterType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counterTypeList;
    }
}
