package Testing.dao;


import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.danilenko.dao.AuditDAO;
import ru.danilenko.liquibase.LiquibaseStart;
import ru.danilenko.mapper.AuditMapper;
import ru.danilenko.util.ConnectionToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * test for audit entity {@link AuditDAO}
 */

public class AuditDAOTest {


    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16"
    );
    private AuditDAO auditDAO;
    private ConnectionToDB connectionToDB;



    @AfterEach
    void afterAll() {
        postgres.stop();
    }
    @BeforeEach
    void setUp() throws SQLException {
        postgres.start();
        connectionToDB = new ConnectionToDB(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
        connectionToDB.getConnection().createStatement().executeUpdate("create schema if not exists Entity;" +
                "create schema if not exists Support;");
        LiquibaseStart.migration(connectionToDB);
        auditDAO = new AuditDAO(new AuditMapper(), connectionToDB);

    }

    /**
     * test addAction method
     */
    @Test
    public void addActionTest(){
        Assertions.assertTrue(auditDAO.addAction("12-2023",1,"see"));
    }

    /**
     * test getAllForUser method
     */
    @Test
    public void getAllForUserTest() {
        auditDAO.addAction("12-2023",1,"see");
        auditDAO.addAction("12-2023",1,"see1");

        Assertions.assertEquals(2,auditDAO.getAllForUser(1).size());

    }
}
