package Testing;


import static org.mockito.Mockito.mock;

public class InputControllerTest {
/*
    InputController inputController;
    CounterTypeDAO counterTypeDAO;


    Scanner scanner ;
    @BeforeEach
    public void init(){
        scanner = mock(Scanner.class);
        userService = mock(UserService.class);
        actionTypeDAO  = new ActionTypeDAO();
        counterTypeDAO = new CounterTypeDAO();
        inputController =  new InputController(counterTypeDAO, userC,scanner);

    }
    @AfterEach
    public void dest(){
        actionTypeDAO  = null;
        scanner = null;
        counterTypeDAO = null;
        inputController =  null;
        userService = null;
    }

    @Test
    public void getOperationTypeForUserTest(){
        List<Integer> listOfOperations = inputController.getOperationType(new User(1,"Test","Test","Test","Test", Roles.ROLE_USER));
        Assertions.assertEquals(4,listOfOperations.size());
    }
    @Test
    public void getOperationTypeForUserTest2(){
        List<Integer> listOfOperations = inputController.getOperationType(new User(1,"Test","Test","Test","Test", Roles.ROLE_USER));
        Assumptions.assumeTrue(listOfOperations.get(0)==1);
    }
    @Test
    public void getOperationTypeForModeratorTest(){
        List<Integer> listOfOperations = inputController.getOperationType(new User(1,"Test","Test","Test","Test", Roles.ROLE_MODERATOR));
        Assertions.assertEquals(5,listOfOperations.size());
    }
    @Test
    public void getOperationTypeForModeratorTest2(){
        List<Integer> listOfOperations = inputController.getOperationType(new User(1,"Test","Test","Test","Test", Roles.ROLE_MODERATOR));
        Assumptions.assumeTrue(listOfOperations.get(4)==5);
    }
    @Test
    public void getOperationTypeForAdminTest(){
        List<Integer> listOfOperations = inputController.getOperationType(new User(1,"Test","Test","Test","Test", Roles.ROLE_ADMIN));
        Assertions.assertEquals(4,listOfOperations.size());
    }
    @Test
    public void getOperationTypeForAdminTest2(){
        List<Integer> listOfOperations = inputController.getOperationType(new User(1,"Test","Test","Test","Test", Roles.ROLE_ADMIN));
        Assumptions.assumeTrue(listOfOperations.get(0)==5);
    }

    @Test
    public void getCounterTypeTest(){
        List<Integer> counterType = inputController.getCounterType();
        Assertions.assertEquals(3, counterType.size());
    }

    @Test
    public void getInputTest(){
        List<Integer> list = new ArrayList<>(List.of(4,5,6,7));
        when(scanner.nextInt()).thenReturn( 1);
        when(scanner.nextLine()).thenReturn("");
        Assertions.assertEquals(4, inputController.getInput(list));
    }

    @Test
    public void getInputTest2(){
        List<Integer> list = new ArrayList<>(List.of(4,5,6,7));
        when(scanner.nextInt()).thenReturn( 0);
        when(scanner.nextLine()).thenReturn("");
        Assertions.assertEquals(0, inputController.getInput(list));
    }
    */


}
