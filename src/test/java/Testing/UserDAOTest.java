package Testing;

import ru.danilenko.dao.UserDAO;
import org.junit.jupiter.api.*;

/**
 * class to test DAO for User entity
 */
public class UserDAOTest {

//    UserDAO userDAO;
//
//    @BeforeEach
//    public void init(){
//        userDAO =  new UserDAO();
//    }
//    @AfterEach
//    public void dest(){
//        userDAO = null;
//    }
//    /**
//     * method checks the getting all users list
//     */
//    @Test
//    public void findAllTest() {
//        Assertions.assertEquals(3,userDAO.findAll().size());
//
//    }
//    /**
//     * method checks the creation of a new user
//     */
//    @Test
//    public void createTest() {
//        userDAO.create("test@email.com","test","test","test");
//        Assumptions.assumeTrue(userDAO.findAll().get(3).getFirstName().equals("test"));
//    }
//    /**
//     * method checks the getting the user based on its email if user exists
//     */
//    @Test
//    public void findByEmailTest() {
//        Assumptions.assumeTrue(userDAO.findByEmail("Admin@mail.ru")!=null);
//    }
//    /**
//     * method checks the getting the user based on its email if user does not exist
//     */
//    @Test
//    public void findByEmailTest2() {
//        Assumptions.assumeTrue(userDAO.findByEmail("admin@mail.ru")==null);
//    }
//
//    /**
//     * method checks the giving the moderator right to user
//     */
//    @Test
//    public void giveRightTest() {
//        userDAO.giveRight(2);
//        Assumptions.assumeTrue(userDAO.findAll().get(0).getRole().equals(Roles.ROLE_MODERATOR));
//    }

}
