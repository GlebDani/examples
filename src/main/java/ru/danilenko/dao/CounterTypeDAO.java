package ru.danilenko.dao;

import ru.danilenko.model.CounterType;

import java.util.ArrayList;
import java.util.List;
/**
 * DAO for CounterType entity
 */
public class CounterTypeDAO {

    /**
     * list to hold counter types
     */
    private List<CounterType> counterTypeList = new ArrayList<>();
    /**
     * static counter to generate counterType_id
     */

    {
        counterTypeList.add(new CounterType(1,"Electricity"));
        counterTypeList.add(new CounterType(2,"Hot water"));
        counterTypeList.add(new CounterType(3,"Cold water"));
    }
    private int counterNumber = counterTypeList.size() + 1;

    /**
     * method add new counter type
     * @param name of the new counter
     */
    public void addNewCounterType(String name){
        counterTypeList.add(new CounterType(counterNumber++,name));
    }
    /**
     * method which
     * @return list of existed counter types
     */
    public List<CounterType> getAll(){
        return  counterTypeList;
    }

    /**
     * method
     * @param counterTypeId id
     * @return counter type based on its
     */
    public CounterType findById(int counterTypeId){
        return counterTypeList.stream().filter(c->c.getCounterTypeId()==counterTypeId).findAny().orElse(null);
    }
}
