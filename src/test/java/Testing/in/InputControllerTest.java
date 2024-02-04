package Testing.in;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.danilenko.in.ActionController;
import ru.danilenko.in.InputController;
import ru.danilenko.model.User;
import ru.danilenko.util.InputAssistant;

/**
 * class to test InputController class {@link InputController}
 */
public class InputControllerTest {

    /**
     * Mock of object of InputAssistant class {@link InputAssistant}
     */
    @Mock
    InputAssistant inputAssistant;
    /**
     * Mock of object of ActionController class {@link ActionController}
     */
    @Mock
    ActionController actionController;
    private AutoCloseable closeable;
    /**
     * Object of Authentication class with Mock injection
     */
    @InjectMocks
    InputController inputController;



    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    /**
     * test calling methods of ActionController class based on the user's input if ADMIN
     */
    @Test
    public void adminActionTest(){
        Mockito.when(inputAssistant.readIntValue(0,4)).thenReturn(3);
        User user1=new User();
        inputController.adminAction(user1);
        Mockito.verify(actionController).usersAudit();
    }
    /**
     * test calling methods of ActionController class based on the user's input if MODERATOR
     */
    @Test
    public void moderatorActionTest(){
        Mockito.when(inputAssistant.readIntValue(0,5)).thenReturn(5);
        User user1=new User();
        inputController.moderatorAction(user1);
        Mockito.verify(actionController).getAllUsersInfo(user1);
    }
    /**
     * test calling methods of ActionController class based on the user's input if USER
     */
    @Test
    public void userAction(){
        Mockito.when(inputAssistant.readIntValue(0,4)).thenReturn(2);
        User user1=new User();
        inputController.userAction(user1);
        Mockito.verify(actionController).insertValue(user1);

    }
}
