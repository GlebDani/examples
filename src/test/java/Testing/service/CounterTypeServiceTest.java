package Testing.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.danilenko.dao.CounterTypeDAO;
import ru.danilenko.model.CounterType;
import ru.danilenko.service.CounterTypeService;

import java.util.ArrayList;
import java.util.List;


public class CounterTypeServiceTest {

    @Mock
    private CounterTypeDAO counterTypeDAO;
    private AutoCloseable closeable;
    /**
     * Object of UserService class with Mock injection
     */
    @InjectMocks
    private CounterTypeService counterTypeService;


    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    /**
     * tests if counterTypeService.addNewCounter() method calls counterTypeDAO.addNewCounter() method with specified parameters
     */
    @Test
    public void addNewCounterTest(){
        counterTypeService.addNewCounter("heating");
        Mockito.verify(counterTypeDAO).addNewCounterType("heating");


    }
    /**
     * tests findById method
     */
    @Test
    public void findByIdTest(){
        CounterType counterType = new CounterType(1, "hot water");
        Mockito.when(counterTypeDAO.findById(1)).thenReturn(counterType);
        Assertions.assertEquals(counterType,counterTypeService.findById(1));

    }
    /**
     * tests getAll method
     */
    @Test
    public void getAllTest() {
        CounterType counterType = new CounterType(1, "hot water");
        CounterType counterType2 = new CounterType(2, "cold water");
        Mockito.when(counterTypeDAO.getAll()).thenReturn(new ArrayList<>(List.of(counterType,counterType2)));
        Assertions.assertEquals(2,counterTypeService.getAll().size());

    }
    /**
     * tests ifExist method
     */
    @Test
    public void ifExistTest(){
        CounterType counterType = new CounterType(1, "hot water");
        CounterType counterType2 = new CounterType(2, "cold water");
        Mockito.when(counterTypeDAO.getAll()).thenReturn(new ArrayList<>(List.of(counterType,counterType2)));
        Assertions.assertTrue(counterTypeService.ifExist("hot water"));

    }
}
