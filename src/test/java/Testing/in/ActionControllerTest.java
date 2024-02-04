package Testing.in;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ru.danilenko.in.ActionController;
import ru.danilenko.model.Audit;
import ru.danilenko.model.Counter;
import ru.danilenko.model.CounterType;
import ru.danilenko.model.User;
import ru.danilenko.service.AuditService;
import ru.danilenko.service.CounterService;
import ru.danilenko.service.CounterTypeService;
import ru.danilenko.service.UserService;
import ru.danilenko.util.InputAssistant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Class tests the UserService class {@link ActionController}
 */
public class ActionControllerTest {
    /**
     * Mock of object of UserDAO class {@link AuditService}
     */
    @Mock
    private AuditService auditService;
    /**
     * Mock of object of UserDAO class {@link CounterService}
     */
    @Mock
    private CounterService counterService;
    /**
     * Mock of object of UserDAO class {@link CounterTypeService}
     */
    @Mock
    private CounterTypeService counterTypeService;
    /**
     * Mock of object of UserDAO class {@link UserService}
     */
    @Mock
    private UserService userService;
    /**
     * Mock of object of UserDAO class {@link InputAssistant}
     */
    @Mock
    private InputAssistant inputAssistant;

    private AutoCloseable closeable;
    /**
     * Object of UserService class with Mock injection
     */
    @InjectMocks
    private ActionController actionController;


    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }
    /**
     * tests  getCurrentValue method
     */
    @Test
    public void getCurrentValueTest(){
        User user = new User(1,"1","1","1","1","1");
        Mockito.when(counterTypeService.getAll()).thenReturn(new ArrayList<>());
        Mockito.when(inputAssistant.readIntValue(0,0)).thenReturn(1);
        Optional<Counter> counter = Optional.of(new Counter());
        Mockito.when(counterService.getCurrentValue(1,1)).thenReturn(counter);
        Mockito.when(counterTypeService.findById(1)).thenReturn(new CounterType());
        Assertions.assertTrue(actionController.getCurrentValue(user));
        Mockito.verify(auditService).addAction(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm").format(LocalDateTime.now()),1,"get current value for "+"null");

    }
    /**
     * tests insertValue method
     */
    @Test
    public void insertValueTest(){
        User user = new User(1,"1","1","1","1","1");
        Mockito.when(counterTypeService.getAll()).thenReturn(new ArrayList<>());
        Mockito.when(inputAssistant.readIntValue(0,0)).thenReturn(1);
        Mockito.when(counterService.getCurrentValue(1,1)).thenReturn(Optional.of(new Counter()));
        Assertions.assertFalse(actionController.ifExist(1,1));
        Mockito.when(inputAssistant.readIntValue(0)).thenReturn(100);
        Mockito.when(counterTypeService.findById(1)).thenReturn(new CounterType());
        Assertions.assertTrue(actionController.insertValue(user));
        Mockito.verify(counterService).insertValue(1, DateTimeFormatter.ofPattern("MM-yyyy").format(LocalDate.now()), 100, 1);
        Mockito.verify(auditService).addAction(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm").format(LocalDateTime.now()),1,"get insert a new value for "+"null");

    }
    /**
     * tests getValueForCertainMonth method
     */
    @Test
    public void getValueForCertainMonthTest(){
        User user = new User(1,"1","1","1","1","1");
        Mockito.when(counterTypeService.getAll()).thenReturn(new ArrayList<>());
        Mockito.when(inputAssistant.readIntValue(0,0)).thenReturn(1);
        Mockito.when(inputAssistant.readIntValue(1,12)).thenReturn(12);
        Mockito.when(counterService.getValueForCertainMonth(1,1, "12-2023")).thenReturn(Optional.of(new Counter()));
        Mockito.when(counterTypeService.findById(1)).thenReturn(new CounterType());
        Assertions.assertTrue(actionController.getValueForCertainMonth(user));
        Mockito.verify(auditService).addAction(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm").format(LocalDateTime.now()),1, "get value for "+"null" +" on "+"12-2023");

    }
    /**
     * tests getHistory method
     */
    @Test
    public void getHistoryTest(){
        User user = new User(1,"1","1","1","1","1");
        Mockito.when(counterTypeService.getAll()).thenReturn(new ArrayList<>());
        Mockito.when(inputAssistant.readIntValue(0,0)).thenReturn(1);
        Mockito.when(counterService.getAllCertainType(1,1)).thenReturn(new ArrayList<>(List.of(new Counter())));
        Mockito.when(counterTypeService.findById(0)).thenReturn(new CounterType());
        Mockito.when(counterTypeService.findById(1)).thenReturn(new CounterType());
        Assertions.assertTrue(actionController.getHistory(user));
        Mockito.verify(auditService).addAction(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm").format(LocalDateTime.now()),1, "get history for "+"null");

    }
    /**
     * tests getAllUsersInfo method
     */
    @Test
    public void getAllUsersInfoTest(){
        User user = new User(1,"1","1","1","1","1");
        //getting user
        Mockito.when(userService.findAllUser()).thenReturn(new ArrayList<>(List.of(new User())));
        Mockito.when(inputAssistant.listOfUsers(Mockito.any())).thenReturn(1);
        //getting counter type
        Mockito.when(counterTypeService.getAll()).thenReturn(new ArrayList<>());
        Mockito.when(inputAssistant.readIntValue(0,0)).thenReturn(1);
        Mockito.when(counterService.getAllCertainType(0,1)).thenReturn(new ArrayList<>(List.of(new Counter())));
        Mockito.when(counterTypeService.findById(1)).thenReturn(new CounterType());
        Mockito.when(counterTypeService.findById(0)).thenReturn(new CounterType());
        Assertions.assertTrue(actionController.getAllUsersInfo(user));
        Mockito.verify(auditService).addAction(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm").format(LocalDateTime.now()),1, "get history for user with user_id " + 0);


    }
    /**
     * tests giveModeratorRights method
     */
    @Test
    public void giveModeratorRightsTest(){
        User user = new User(1,"1","1","1","1","1");
        //getting user
        Mockito.when(userService.findAllUser()).thenReturn(new ArrayList<>(List.of(new User())));
        Mockito.when(inputAssistant.listOfUsers(Mockito.any())).thenReturn(1);
        Assertions.assertTrue(actionController.giveModeratorRights(user));
        Mockito.verify(userService).giveRight(0);
        Mockito.verify(auditService).addAction(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm").format(LocalDateTime.now()),1, "gave moderator right to user with userId "+ 0);



    }
    /**
     * tests usersAudit method
     */
    @Test
    public void usersAuditTest(){
        Mockito.when(userService.findAll()).thenReturn(new ArrayList<>(List.of(new User())));
        Mockito.when(inputAssistant.listOfUsers(Mockito.any())).thenReturn(1);
        Mockito.when(auditService.getAllForUser(0)).thenReturn(new ArrayList<>(List.of(new Audit())));
        Assertions.assertTrue(actionController.usersAudit());
    }
    /**
     * tests addNewCounter method
     */
    @Test
    public  void addNewCounterTest(){
        User user = new User(1,"1","1","1","1","1");
        Mockito.when(inputAssistant.nextLine()).thenReturn("s");
        Mockito.when(counterTypeService.ifExist("s")).thenReturn(false);
        Assertions.assertTrue(actionController.addNewCounter(user));
        Mockito.verify(counterTypeService).addNewCounter("s");
        Mockito.verify(auditService).addAction(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm").format(LocalDateTime.now()),1, "add new counter type " + "s");


    }
    /**
     * tests ifExist method
     */
    @Test
    public void ifExistTest(){
        Mockito.when(counterService.getCurrentValue(1,1)).thenReturn(Optional.of(new Counter()));
        Assertions.assertFalse(actionController.ifExist(1,1));
    }
    /**
     * tests getCounterType method
     */
    @Test
    public void getCounterTypeTest(){
        Mockito.when(counterTypeService.getAll()).thenReturn(new ArrayList<>());
        Mockito.when(inputAssistant.readIntValue(0,0)).thenReturn(1);
        Assertions.assertEquals(1, actionController.getCounterType());
    }

}
