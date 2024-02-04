package Testing.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.danilenko.mapper.CounterTypeMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class tests the CounterTypeMapper class {@link CounterTypeMapper}
 */
public class CounterTypeMapperTest {
    /**
     * Mock of object of ResultSet class {@link ResultSet}
     */
    @Mock
    private ResultSet resultSet;
    private AutoCloseable closeable;
    /**
     * Object of CounterTypeMapper class
     */
    private CounterTypeMapper counterTypeMapper = new CounterTypeMapper();


    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }
    /**
     * tests mapToCounterType method
     */
    @Test
    public void mapToCounterTypeTest() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt("countertype_id")).thenReturn(1);
        Mockito.when(resultSet.getString("countertype_id")).thenReturn("see");

        Assertions.assertEquals("see",counterTypeMapper.mapToCounterType(resultSet).get(0).getDescription());
    }
}
