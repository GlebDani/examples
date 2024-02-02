package Testing;

import ru.danilenko.dao.CounterDAO;
import ru.danilenko.model.Counter;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
/**
 * class to test DAO for Counter entity
 */
public class CounterDAOTest {
//    CounterDAO counterDAO;
//
//    @BeforeEach
//    public void init(){
//        counterDAO = new CounterDAO();
//    }
//    @AfterEach
//    public void dest(){
//        counterDAO = null;
//    }
//    /**
//     * method checks the getting all counter notes list
//     */
//    @Test
//    public void getAllTest(){
//        List<Counter> counterList = counterDAO.getAll();
//        Assumptions.assumeTrue(counterList.size()==8);
//    }
//    /**
//     * method checks the creation of a new counter note
//     */
//    @Test
//    public void createNewTest(){
//        counterDAO.createNew(5, LocalDate.now(),100, 5);
//       Assertions.assertEquals(9,counterDAO.getAll().size());
//    }
//
//    /**
//     * method checks the getting a list of counter notes of certain type
//     */
//    @Test
//    public void getAllCertainType(){
//        Assertions.assertEquals(1,counterDAO.getAllCertainType(3,3).size());
//    }
//    /**
//     * method checks the getting current value if it exists
//     */
//    @Test
//    public void getCurrentValueDaoTest(){
//        Assertions.assertEquals(450,counterDAO.getCurrentValueDao(2,1).getCounterMeasure());
//
//    }
//    /**
//     * method checks the getting current value if it does not exist
//     */
//    @Test
//    public void getCurrentValueDaoTest2(){
//        Assertions.assertEquals(150,counterDAO.getCurrentValueDao(3,3).getCounterMeasure());
//
//    }
//    /**
//     * method checks the getting list of counter notes by user_id
//     */
//    @Test
//    public void findByIdTest(){
//        Assumptions.assumeTrue(counterDAO.findById(3).size() == 2);
//
//    }
//    /**
//     * method checks the getting counter note on certain month if it exists
//     */
//    @Test
//    public void getValueForCertainMonthTestExist(){
//        String date = DateTimeFormatter.ofPattern("MM-yyyy").format(LocalDate.of(2023,12,1));
//        Assumptions.assumeTrue(counterDAO.getValueForCertainMonth(2,1,date) != null);
//    }
//    /**
//     * method checks the getting counter note on certain month if it does not exist
//     */
//    @Test
//    public void getValueForCertainMonthTestNotExist(){
//        String date = DateTimeFormatter.ofPattern("MM-yyyy").format(LocalDate.of(2023,12,1));
//        Assumptions.assumeTrue(counterDAO.getValueForCertainMonth(4,1,date) == null);
//    }
}
