package Testing.dao;


import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.danilenko.dao.CounterTypeDAO;
import ru.danilenko.liquibase.LiquibaseStart;
import ru.danilenko.mapper.CounterTypeMapper;
import ru.danilenko.model.CounterType;
import ru.danilenko.util.ConnectionToDB;

import java.sql.SQLException;

/**
 * test for counterType entity {@link CounterTypeDAO}
 */
public class CounterTypeDAOTest {




    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16"
    );
    private CounterTypeDAO counterTypeDAO;
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
        counterTypeDAO = new CounterTypeDAO(new CounterTypeMapper(), connectionToDB);

    }
    /**
     * test addNewCounterType method
     */
    @Test
    public void addNewCounterTypeTest(){
        Assertions.assertTrue(counterTypeDAO.addNewCounterType("Heating"));

    }
    /**
     * test createNew method
     */
    @Test
    public void  getAll(){
        Assertions.assertEquals(3, counterTypeDAO.getAll().size());
    }

    /**
     * test createNew method
     */
    @Test
    public void findByIdTest(){
        Assertions.assertEquals("Electricity", counterTypeDAO.findById(1).getDescription());
    }
}
