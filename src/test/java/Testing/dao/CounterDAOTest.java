package Testing.dao;


import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.danilenko.dao.AuditDAO;
import ru.danilenko.dao.CounterDAO;
import ru.danilenko.liquibase.LiquibaseStart;
import ru.danilenko.mapper.AuditMapper;
import ru.danilenko.mapper.CounterMapper;
import ru.danilenko.util.ConnectionToDB;

import java.sql.SQLException;


/**
 * test for counter entity {@link CounterDAO}
 */
public class CounterDAOTest{


    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16"
    );
   private  CounterDAO counterDAO;
    private  ConnectionToDB connectionToDB;


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
        counterDAO = new CounterDAO(new CounterMapper(), connectionToDB);

    }


    /**
     * test createNew method
     */
    @Test
    public void createNewTest() {
        Assertions.assertTrue(counterDAO.createNew(1,"02-2024",150,1));

    }

    /**
     * test getAllCertainType method
     */
    @Test
    public void getAllCertainTypeTest() {
        counterDAO.createNew(1,"02-2024",50,1);
        counterDAO.createNew(1,"01-2024",150,1);

        Assertions.assertEquals(2, counterDAO.getAllCertainType(1,1).size());

    }




}
