package Testing.service;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ru.danilenko.dao.UserDAO;
import ru.danilenko.model.User;
import ru.danilenko.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Class tests the UserService class {@link UserService}
 */
public class UserServiceTest {
    /**
     * Mock of object of UserDAO class {@link UserDAO}
     */
    @Mock
    private UserDAO userDAO;
    private AutoCloseable closeable;
    /**
     * Object of UserService class with Mock injection
     */
    @InjectMocks
    private UserService userService;


    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    /**
     * tests findAllUser method
     */
    @Test
    public void findAllUserTest(){
        User user1 = new User(1,"1","1","1","1","USER");
        User user2 = new User(2,"2","2","2","2","USER");
        Mockito.when(userDAO.findAllUser()).thenReturn(new ArrayList<>(List.of(user1,user2)));
        List<User> userList = userService.findAllUser();
        Assertions.assertEquals(2, userList.size());

    }
    /**
     * tests findAll method
     */
    @Test
    public void findAllTest(){
        User user1 = new User(1,"1","1","1","1","USER");
        User user2 = new User(2,"2","2","2","2","USER");
        User user3 = new User(3,"3","3","3","3","ADMIN");
        Mockito.when(userDAO.findAll()).thenReturn(new ArrayList<>(List.of(user1,user2,user3)));
        List<User> userList = userService.findAll();
        Assertions.assertEquals(3, userList.size());
    }

    /**
     * tests if userService.giveRight() method  calls userDAO.giveRight() method with specified parameters
     */
    @Test
    public void giveRightTest(){
        userService.giveRight(1);
        Mockito.verify(userDAO).giveRight(1,"MODERATOR");
    }
    /**
     * tests findByEmail method
     */
    @Test
    public void findByEmailTest() {
        User user1 = new User(1,"1","1","1","1","USER");
        Mockito.when(userDAO.findByEmail("1")).thenReturn(user1);
        Assertions.assertEquals(user1, userService.findByEmail("1"));

    }
    /**
     * tests if userService.create() method calls userDAO.create() method with specified parameters
     */
    @Test
    public void createTest() {
        userService.create("1","1","1","1","USER");
        Mockito.verify(userDAO).create("1","1","1","1","USER");

    }
}
