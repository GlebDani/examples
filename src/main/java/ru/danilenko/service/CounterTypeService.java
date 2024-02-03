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

    public CounterType findById(int counterTypeId){
        return counterTypeDAO.findById(counterTypeId);
    }

    public List<CounterType> getAll() {
        return counterTypeDAO.getAll();
    }

    public boolean ifExist(String desc){
        List<CounterType> list = getAll();
        return list.stream().anyMatch(c-> c.getDescription().equalsIgnoreCase(desc));

    }
}
