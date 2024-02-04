package Testing.dao;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.danilenko.dao.CounterDAO;
import ru.danilenko.dao.UserDAO;
import ru.danilenko.liquibase.LiquibaseStart;
import ru.danilenko.mapper.CounterMapper;
import ru.danilenko.mapper.UserMapper;
import ru.danilenko.util.ConnectionToDB;

import java.sql.SQLException;

/**
 * Test for user entity {@link  UserDAO}
 */

public class UserDAOTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16"
    );
    private UserDAO userDAO;
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
       userDAO = new UserDAO(new UserMapper(),connectionToDB);

    }

    /**
     * test create method
     */
    @Test
    public void createTest() {
        Assertions.assertTrue(userDAO.create("test","test","test","test","test"));

    }
    /**
     * test findByEmail method
     */
    @Test
    public void findByEmailTest() {
        Assertions.assertEquals(1,userDAO.findByEmail("Admin@mail.ru").getId());

    }

    /**
     * test findById method
     */
    @Test
    public void findByIdTest() {
        Assertions.assertEquals("User2@mail.ru",userDAO.findById(2).getEmail());

    }

    /**
     * findAllUser
     */
    @Test
    public void findAllUserTest() {
        Assertions.assertEquals(3,userDAO.findAllUser().size());

    }

    /**
     * test findAll method
     */
    @Test
    public void findAllTest() {
        Assertions.assertEquals(4,userDAO.findAll().size());
    }

    /**
     * test giveRight method
     */
    @Test
    public void giveRightTest() {
        userDAO.giveRight(1,"mo");
        Assertions.assertEquals("mo",userDAO.findById(1).getRole());

    }
}




