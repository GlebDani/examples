package Testing.in;

import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.danilenko.in.Authentication;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.danilenko.model.User;
import ru.danilenko.service.UserService;
import ru.danilenko.util.InputAssistant;

/**
 * class to test Authorisation class {@link Authentication}
 */
public class AuthenticationTest {
    /**
     * Mock of object of InputAssistant class {@link InputAssistant}
     */
    @Mock
    InputAssistant inputAssistant;
    /**
     * Mock of object of UserService class {@link UserService}
     */
    @Mock
    UserService userService;
    private AutoCloseable closeable;
    /**
     * Object of Authentication class with Mock injection
     */
    @InjectMocks
    Authentication authentication;



    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }


    @Test
    public void startUpTestLogIn(){
        User user = new User(1,"1@mail.ru","1","1","1","USER");
        Mockito.when(inputAssistant.nextLine()).thenReturn("l");
        Mockito.when(inputAssistant.getInput("To quit type \"/\" \nEmail")).thenReturn("1@mail.ru");
        Mockito.when(inputAssistant.getInput("Password")).thenReturn("1");
        Mockito.when(userService.findByEmail("1@mail.ru")).thenReturn(user);
        Assertions.assertEquals(user, authentication.startUp());

    }
    @Test
    public void startUpTestSingUp(){
        User user = new User(1,"1@mail.ru","1","1","1","USER");
        Mockito.when(inputAssistant.nextLine()).thenReturn("s");
        Mockito.when(inputAssistant.getInput("To quit type \"/\" \nEmail")).thenReturn("1@mail.ru");
        Mockito.when(inputAssistant.getInput("Password")).thenReturn("1");
        Mockito.when(inputAssistant.getInput("First name")).thenReturn("1");
        Mockito.when(inputAssistant.getInput("Last name")).thenReturn("1");
        Mockito.when(userService.findByEmail("1@mail.ru")).thenReturn(null).thenReturn(user);
        Mockito.when(userService.create("1@mail.ru","1","1","1", "USER")).thenReturn(true);
        Assertions.assertEquals(user, authentication.startUp());

    }




}
