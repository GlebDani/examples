package Testing.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.danilenko.mapper.CounterMapper;
import ru.danilenko.mapper.UserMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class tests the UserMapper class {@link UserMapper}
 */
public class UserMapperTest {
    /**
     * Mock of object of ResultSet class {@link ResultSet}
     */
    @Mock
    private ResultSet resultSet;
    private AutoCloseable closeable;
    /**
     * Object of UserMapperclass
     */
    private UserMapper userMapper = new UserMapper();


    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }
    /**
     * tests mapToUser method
     */
    @Test
    public void mapToUserTest() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt("user_id")).thenReturn(1);
        Mockito.when(resultSet.getString("email")).thenReturn("1@mail.ru");
        Mockito.when(resultSet.getString("password")).thenReturn("1");
        Mockito.when(resultSet.getString("first_name")).thenReturn("1");
        Mockito.when(resultSet.getString("last_name")).thenReturn("1");
        Mockito.when(resultSet.getString("role")).thenReturn("USER");

        Assertions.assertEquals("1@mail.ru",userMapper.mapToUser(resultSet).get(0).getEmail());
    }
}
