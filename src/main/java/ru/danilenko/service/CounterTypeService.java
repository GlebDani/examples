package ru.danilenko.service;

import lombok.AllArgsConstructor;
import ru.danilenko.dao.CounterTypeDAO;
import ru.danilenko.model.CounterType;

import java.util.List;

@AllArgsConstructor
public class CounterTypeService {

    CounterTypeDAO counterTypeDAO;

    /**
     * method add new counter type
     * @param desc name of counter
     */
    public boolean addNewCounter(String desc){
        if(ifExist(desc))
            return false;
        return counterTypeDAO.addNewCounterType(desc);
    }

    /**
     * return counter Type based on its Id
     * @param counterTypeId id
     * @return CounterType
     */
    public CounterType findById(int counterTypeId){
        return counterTypeDAO.findById(counterTypeId);
    }

    /**
     * return list of all Counter types
     * @return List<CounterType>
     */
    public List<CounterType> getAll() {
        return counterTypeDAO.getAll();
    }

    /**
     * Check if the counter with desc exists
     * @param desc counter description
     * @return true if exist
     */
    public boolean ifExist(String desc){
        List<CounterType> list = getAll();
        return list.stream().anyMatch(c-> c.getDescription().equalsIgnoreCase(desc));

    }
}
