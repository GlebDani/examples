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
    public void addNewCounter(String desc){
        counterTypeDAO.addNewCounterType(desc);
    }

    public CounterType findById(int counterTypeId){
        return counterTypeDAO.findById(counterTypeId);
    }

    public List<CounterType> getAll() {
        return counterTypeDAO.getAll();
    }
}
