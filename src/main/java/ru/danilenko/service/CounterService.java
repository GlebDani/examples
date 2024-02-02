package ru.danilenko.service;

import lombok.AllArgsConstructor;
import ru.danilenko.dao.CounterDAO;
import ru.danilenko.model.Counter;
import ru.danilenko.model.CounterType;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CounterService {

    CounterDAO counterDAO;

    /**
     * method return List of counter notes of certain type
     * @param userId id of logged user
     * @param counterType counterTypeId
     * @return List<Counter> of chosen counter type
     */
    public List<Counter> getAllCertainType(int userId, int counterType){
        return counterDAO.getAllCertainType(userId,counterType);
    }

    /**
     * method which
     * @param userId users id
     * @param counterTypeId counter type
     * @return last added note for certain User and certain type of counter
     */
    public Optional<Counter> getCurrentValue(int userId, int counterTypeId) {
        List<Counter> tempList =  counterDAO.getAllCertainType(userId, counterTypeId);
        return Optional.of(tempList.get(tempList.size()-1));
  }

    /**
     * method inserts value if not exists
     * @param userId id of logged user
     * @param date current date MM-yyyy
     * @param counterTypeId counter Type Id
     * @param counterValue counterTypeValue
     */
    public void insertValue(int userId,String date, int counterValue,int counterTypeId){
        counterDAO.createNew(userId, date, counterValue, counterTypeId);
    }
    /**
     * method return Optional object of Counter class for certain month
     * @param userId id of logged user
     * @param counterTypeId counterType_id
     * @param date date of
     * @return Optional<Counter> if value is shown and false if not
     */
    public Optional<Counter>  getValueForCertainMonth(int userId, int counterTypeId, String date){
        return counterDAO.getAllCertainType(userId,counterTypeId).stream().filter(c->c.getDate().equals(date)).findAny();
    }


    /**
     * returns all counters for certain user
     * @param userId  id for chosen user
     * @return List<Counter>
     */

    public List<Counter> findByUserId(int userId){
        return counterDAO.findByUserId(userId);
    }

}
