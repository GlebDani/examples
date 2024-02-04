package Testing.service;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.danilenko.dao.CounterDAO;
import ru.danilenko.dao.UserDAO;
import ru.danilenko.model.Counter;
import ru.danilenko.service.CounterService;
import ru.danilenko.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class tests the UserService class {@link CounterService}
 */
public class CounterServiceTest {

    /**
     * Mock of object of CounterDAO class {@link CounterDAO}
     */
    @Mock
    private CounterDAO counterDAO;
    private AutoCloseable closeable;
    /**
     * Object of CounterService class with Mock injection
     */
    @InjectMocks
    private CounterService counterService;


    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    /**
     * tests getAllCertainType method
     */
    @Test
    public void getAllCertainTypeTest(){
        Counter counter1 = new Counter(1,1,"11-2012",100,1);
        Counter counter2 = new Counter(2,1,"12-2012",200,1);
        Mockito.when(counterDAO.getAllCertainType(1,1)).thenReturn(new ArrayList<>(List.of(counter1,counter2)));
        Assertions.assertEquals(2, counterService.getAllCertainType(1,1).size());
    }
    /**
     * tests getCurrentValue method
     */
    @Test
    public void getCurrentValueTest() {
        Counter counter1 = new Counter(1,1,"11-2012",100,1);
        Mockito.when(counterDAO.getAllCertainType(1, 1)).thenReturn(new ArrayList<>(List.of(counter1)));
        Assertions.assertTrue(counterService.getCurrentValue(1,1).isPresent());
    }
    /**
     * tests if counterService.insertValue() method  calls counterDAO.createNew() method with specified parameters
     */
    @Test
    public void insertValue(){
        counterService.insertValue(1,"01-2024",120,2);
        Mockito.verify(counterDAO).createNew(1,"01-2024",120,2);
    }
    /**
     * tests getValueForCertainMonth method
     */
    @Test
    public void getValueForCertainMonthTest(){

       Mockito.when(counterDAO.getAllCertainType(1,1)).thenReturn(new ArrayList<>());
       Assertions.assertFalse(counterService.getValueForCertainMonth(1,1,"12-2023").isPresent());
    }
}
