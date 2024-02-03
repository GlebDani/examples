package ru.danilenko.dao;

import lombok.AllArgsConstructor;
import ru.danilenko.mapper.CounterTypeMapper;
import ru.danilenko.model.CounterType;
import ru.danilenko.model.User;
import ru.danilenko.util.ConnectionToDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * DAO for CounterType entity
 */
@AllArgsConstructor
public class CounterTypeDAO {

    private CounterTypeMapper counterTypeMapper;


    /**
     * method add new counter type
     * @param name of the new counter
     */
    public boolean addNewCounterType(String name){
        try {
            PreparedStatement statement = ConnectionToDB.getConnection().
                    prepareStatement("insert into entity.countertype(description) values(?)");
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;

    }
    /**
     * method which
     * @return list of existed counter types
     */
    public List<CounterType> getAll(){
        List<CounterType> counterTypeList = new ArrayList<>();
        try {
            Statement statement = ConnectionToDB.getConnection().createStatement();
            String SQL = "select * from entity.countertype";
            ResultSet resultSet = statement.executeQuery(SQL);
            counterTypeList = counterTypeMapper.mapToCounterType(resultSet);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  counterTypeList;
    }

    /**
     * method
     * @param counterTypeId id
     * @return counter type based on its
     */
    public CounterType findById(int counterTypeId){
        CounterType counterType;
        try {
            PreparedStatement statement = ConnectionToDB.getConnection().prepareStatement("select * from entity.countertype where countertype_id = ?");
            statement.setInt(1, counterTypeId);
            ResultSet resultSet = statement.executeQuery();
            List<CounterType> counterTypeList= counterTypeMapper.mapToCounterType(resultSet);
            counterType = (counterTypeList.isEmpty())?null:counterTypeList.get(0);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return  counterType;
    }
}
