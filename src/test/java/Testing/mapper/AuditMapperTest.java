package Testing.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.danilenko.dao.UserDAO;
import ru.danilenko.mapper.AuditMapper;
import ru.danilenko.model.Audit;
import ru.danilenko.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * Class tests the AuditMapper class {@link AuditMapper}
 */
public class AuditMapperTest {
    /**
     * Mock of object of ResultSet class {@link ResultSet}
     */
    @Mock
    private ResultSet resultSet;
    private AutoCloseable closeable;
    /**
     * Object of AuditMapper class
     */
    private AuditMapper auditMapper = new AuditMapper();


    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }
    /**
     * tests mapToAudit method
     */
    @Test
    public void mapToAuditTest() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt("action_id")).thenReturn(1);
        Mockito.when(resultSet.getString("date")).thenReturn("12-2023");
        Mockito.when(resultSet.getInt("user_id")).thenReturn(1);
        Mockito.when(resultSet.getString("description")).thenReturn("see");
        Assertions.assertEquals("12-2023",auditMapper.mapToAudit(resultSet).get(0).getDate());
    }
}
