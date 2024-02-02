package Testing;

import static org.mockito.Mockito.mock;

/**
 * class to test DAO for User service
 */
public class UserServiceTest {

//    Scanner scanner;
//    AuditDAO auditDAO;
//
//    UserDAO userDAO;
//
//    CounterDAO counterDAO;
//
//    CounterTypeDAO counterTypeDAO;
//
//    UserService userService;
//
//    @BeforeEach
//    public void init(){
//        scanner = mock(Scanner.class);
//        userDAO = new UserDAO();
//        auditDAO = new AuditDAO();
//        counterDAO = new CounterDAO();
//        counterTypeDAO = new CounterTypeDAO();
//        userService = new UserService(scanner, auditDAO,userDAO,counterDAO,counterTypeDAO);
//
//
//    }
//    @AfterEach
//    public void dest(){
//        scanner = null;
//        userService = null;
//        userDAO = null;
//        auditDAO = null;
//        counterDAO = null;
//        counterTypeDAO = null;
//    }
//    /**
//     * method checks the getting current value if it exists
//     */
//
//    @Test
//    public void getCurrentValueTestWhenExist(){
//        User user = userDAO.findByEmail("User2@mail.ru");
//        Assertions.assertTrue(userService.getCurrentValue(user, 1));
//    }
//    /**
//     * method checks the getting current value if it  does not exist
//     */
//    @Test
//    public void getCurrentValueTestWhenNotExist(){
//        User user = userDAO.findByEmail("Admin@mail.ru");
//        Assertions.assertFalse(userService.getCurrentValue(user, 1));
//    }
//    /**
//     * method checks the creation of a new counter note if it does not exist
//     */
//    @Test
//    public void insertValueNotExistTest(){
//        User user = userDAO.findByEmail("User4@mail.ru");
//        when(scanner.nextInt()).thenReturn(150);
//        when(scanner.nextLine()).thenReturn("");
//       Assumptions.assumeTrue(userService.insertValue(user, 3));
//    }
//    /**
//     * method checks the creation of a new counter note if it exists
//     */
//    @Test
//    public void insertValueExistTest(){
//        User user = userDAO.findByEmail("User4@mail.ru");
//        when(scanner.nextInt()).thenReturn(150);
//        when(scanner.nextLine()).thenReturn("");
//        userService.insertValue(user, 3);
//        Assumptions.assumeFalse(userService.insertValue(user, 3));
//    }
//    /**
//     * method checks the getting counter note value on certain month if it exists
//     */
//    @Test
//    public void getValueForCertainMonthExistTest(){
//        User user = userDAO.findByEmail("User2@mail.ru");
//        when(scanner.nextInt()).thenReturn(12);
//        when(scanner.nextLine()).thenReturn("");
//        Assumptions.assumeTrue(userService.getValueForCertainMonth(user,1));
//    }
//    /**
//     * method checks the getting counter note value on certain month if it does not exist
//     */
//    @Test
//    public void getValueForCertainMonthNotExistTest(){
//        User user = userDAO.findByEmail("User4@mail.ru");
//        when(scanner.nextInt()).thenReturn(12);
//        when(scanner.nextLine()).thenReturn("");
//        Assumptions.assumeFalse(userService.getValueForCertainMonth(user,1));
//    }
//    /**
//     * method checks the getting counter note on certain month if it exists
//     */
//    @Test
//    public void getHistoryTest(){
//        User user = userDAO.findByEmail("User2@mail.ru");
//        Assumptions.assumeTrue(userService.getHistory(user,1));
//    }
//    /**
//     * method checks the getting user counter notes if user exists
//     */
//    @Test
//    public void getAllUsersInfoTest(){
//        User user = userDAO.findByEmail("Admin@mail.ru");
//
//        when(scanner.nextInt()).thenReturn(1);
//        when(scanner.nextLine()).thenReturn("");
//        Assumptions.assumeTrue(userService.getAllUsersInfo(user));
//    }
//    /**
//     * method checks the getting user counter notes if user does not exist
//     */
//    @Test
//    public void getAllUsersInfoTest2(){
//        User user = userDAO.findByEmail("Admin@mail.ru");
//
//        when(scanner.nextInt()).thenReturn(0);
//        when(scanner.nextLine()).thenReturn("");
//        Assumptions.assumeFalse(userService.getAllUsersInfo(user));
//    }
//    /**
//     * method checks the giving moderator rights to a user
//     */
//    @Test
//    public void giveModeratorRightsTest(){
//        User user = userDAO.findByEmail("Admin@mail.ru");
//        when(scanner.nextInt()).thenReturn(1);
//        when(scanner.nextLine()).thenReturn("");
//        Assumptions.assumeTrue(userService.giveModeratorRights(user) && userDAO.findAll().get(0).getRole()== Roles.ROLE_MODERATOR);
//    }
//    /**
//     * method checks adding new counter type
//     */
//    @Test
//    public  void addNewCounterInsertingTest(){
//        User user = userDAO.findByEmail("Admin@mail.ru");
//        when(scanner.nextLine()).thenReturn("heating");
//        Assumptions.assumeTrue(userService.addNewCounter(user) && counterTypeDAO.findById(4).getDescription().equals("heating"));
//    }
//    /**
//     * method checks quiting when a new counter type is creating
//     */
//    @Test
//    public  void addNewCounterQuitingTest(){
//        User user = userDAO.findByEmail("Admin@mail.ru");
//        when(scanner.nextLine()).thenReturn("/");
//        Assumptions.assumeFalse(userService.addNewCounter(user));
//    }
//

}
