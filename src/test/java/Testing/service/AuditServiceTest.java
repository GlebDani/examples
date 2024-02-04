package Testing.service;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.danilenko.dao.AuditDAO;
import ru.danilenko.dao.CounterDAO;
import ru.danilenko.model.Audit;
import ru.danilenko.service.AuditService;

import java.util.ArrayList;
import java.util.List;


/**
 * Class tests the UserService class {@link AuditService}
 */
public class AuditServiceTest {
    /**
     * Mock of object of AuditDAO class {@link AuditDAO}
     */
    @Mock
    private AuditDAO auditDAO;
    private AutoCloseable closeable;
    /**
     * Object of AuditService class with Mock injection
     */
    @InjectMocks
    private AuditService auditService;


    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    /**
     * tests if auditService.addAction() method  calls auditDAO.addAction() method with specified parameters
     */
    @Test
    public void addActionTest(){
        auditService.addAction("12-2023",1, "see");
        Mockito.verify(auditDAO).addAction("12-2023",1, "see");
    }

    /**
     * tests getAllForUser method
     */
    @Test
    public void getAllForUserTest() {
        Audit audit1 = new Audit();
        Audit audit2 = new Audit();
        Mockito.when(auditDAO.getAllForUser(1)).thenReturn(new ArrayList<>(List.of(audit1,audit2)));
        Assertions.assertEquals(2, auditService.getAllForUser(1).size());
    }
}
