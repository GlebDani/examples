package ru.danilenko.mapper;


import ru.danilenko.model.CounterType;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * class for Audit {@link  CounterType} mapping
 */
public class CounterTypeMapper {

    /**
     * method returns list of obtained counter types
     * @param resultSet after sql query
     * @return List<CounterType>
     */
    public List<CounterType> mapToCounterType(ResultSet resultSet) {
        List<CounterType> counterTypeList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                CounterType counterType = new CounterType();
                counterType.setCounterTypeId(resultSet.getInt("countertype_id"));
                counterType.setDescription(resultSet.getString("description"));
                counterTypeList.add(counterType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counterTypeList;
    }
}
