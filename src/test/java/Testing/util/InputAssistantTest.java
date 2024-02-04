package Testing.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ru.danilenko.model.User;
import ru.danilenko.service.UserService;
import ru.danilenko.util.InputAssistant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class tests the InputAssistant class {@link InputAssistant}
 */
public class InputAssistantTest {
    /**
     * Mock of object of Scanner class {@link Scanner}
     */
    @Mock
    private Scanner scanner;
    private AutoCloseable closeable;
    /**
     * Object of UserService class with Mock injection
     */
    @InjectMocks
    private InputAssistant inputAssistant;


    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }
    /**
     * tests getInput() method
     */
    @Test
    public void getInputTest(){
        Mockito.when(scanner.nextLine()).thenReturn("1");
        Assertions.assertEquals("1",inputAssistant.getInput("password"));

    }
    /**
     * tests calling scanner.nextLine() via inputAssistant.nextLine() method
     */
    @Test
    public void nextLineTest(){
        inputAssistant.nextLine();
        Mockito.verify(scanner).nextLine();
    }

    /**
     * tests readIntValue(int leftBoundary, int rightBoundary) method
     */
    @Test
    public void readIntValueTwoBoundariesTest(){
        Mockito.when(scanner.nextLine()).thenReturn("4");
        Assertions.assertEquals(4,inputAssistant.readIntValue(1,5));
    }
    /**
     * tests readIntValue(int leftBoundary) method
     */
    @Test
    public void readIntValueOneBoundariesTest(){
        Mockito.when(scanner.nextLine()).thenReturn("4");
        Assertions.assertEquals(4,inputAssistant.readIntValue(1));

    }
    /**
     * tests listOfUsers method
     */
    @Test
    public void listOfUsersTest(){
        User user1 = new User();
        User user2 = new User();
        Mockito.when(scanner.nextLine()).thenReturn("1");
        Assertions.assertEquals(1, inputAssistant.listOfUsers(new ArrayList<>(List.of(user1,user2))));
    }

}
