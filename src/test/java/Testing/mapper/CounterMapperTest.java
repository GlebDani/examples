package Testing.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.danilenko.mapper.CounterMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Class tests the CounterMapper class {@link CounterMapper}
 */

public class CounterMapperTest {
    /**
     * Mock of object of ResultSet class {@link ResultSet}
     */
    @Mock
    private ResultSet resultSet;
    private AutoCloseable closeable;
    /**
     * Object of CounterMapper class
     */
    private CounterMapper counterMapper = new CounterMapper();


    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }
    /**
     * tests mapToCounter method
     */
    @Test
    public void mapToCounterTest() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt("counter_id")).thenReturn(1);
        Mockito.when(resultSet.getInt("user_id")).thenReturn(1);
        Mockito.when(resultSet.getString("date")).thenReturn("10-2023");
        Mockito.when(resultSet.getInt("counterMeasure")).thenReturn(100);
        Mockito.when(resultSet.getInt("counterType_id")).thenReturn(1);
        Assertions.assertEquals("10-2023",counterMapper.mapToCounter(resultSet).get(0).getDate());
    }
}
